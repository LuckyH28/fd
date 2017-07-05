package com.aisino.domain.Servlet;

import com.aisino.domain.busi.service.IBusiService;

import static com.aisino.domain.common.enums.ReturnCodeEnum.*;


import com.aisino.domain.common.enums.BusiConstant;
import com.aisino.domain.common.util.CommonUtil;
import com.aisino.domain.common.enums.CzlxConstant;
import com.aisino.domain.protocol.bean.busi.ResponseInfoEntity;
import com.aisino.domain.protocol.bean.busi.ScheduleJobEntity;
import com.aisino.domain.protocol.bean.busi.TmpBusiEntity;
import com.aisino.domain.rabbitmq.core.RabbitmqListener;
import com.google.gson.JsonSyntaxException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by LuckyH on 2017-03-15.
 */
public class TmpInnerEntranceServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1891622684270253336L;

	private static final Logger logger = LogManager
			.getLogger(TmpInnerEntranceServlet.class.getName());

	private String dateByFormat;
	private IBusiService busiService;
	private RabbitmqListener rabbitmqListener;

	private static ResponseInfoEntity responseInfoEntity = new ResponseInfoEntity();

	/**
	 * EntranceServlet初始化
	 * 
	 * @throws ServletException
	 */
	public void init() throws ServletException {

	}

	/**
	 * EntranceServlet注销
	 */
	public void destroy() {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String responseXML = "";
		dateByFormat = CommonUtil.getDateByFormat(new Date(),
				"yyyy/MM/dd HH:mm:ss");
		long start = System.currentTimeMillis();
		try {

			// 获取请求报文,并设置编码方式
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			ServletInputStream inputStream = request.getInputStream();
			String requestXML = IOUtils.toString(inputStream, "UTF-8");
			IOUtils.closeQuietly(inputStream);

			if (!StringUtils.isEmpty(requestXML)) {
				logger.info("--线程(ID:" + Thread.currentThread().getId()
						+ ")，接口调用开始，请求报文如下:\n" + requestXML);
				ApplicationContext ac = WebApplicationContextUtils
						.getWebApplicationContext(request.getSession()
								.getServletContext());
				// 处理请求，返回响应报文
				responseInfoEntity = process(requestXML, ac);
			} else {
				responseInfoEntity.setCode(RequestIsEmpty.getCode());
				responseInfoEntity.setMessage(RequestIsEmpty.getMessage());
			}

		} catch (JsonSyntaxException jsonException) { // 发票数据不合法
			logger.error("--线程(ID:" + Thread.currentThread().getId()
					+ "),业务处理发生异常，详情为：------------------------------"
					+ jsonException);
			responseInfoEntity.setCode(JsonSyntaxException.getCode());
			responseInfoEntity.setMessage(JsonSyntaxException.getMessage());

		}catch (Exception e) {
			logger.error("--线程(ID:" + Thread.currentThread().getId()
					+ "),业务处理发生异常，详情为：------------------------------"
					+ e);
			responseInfoEntity.setCode(Fail.getCode());
			responseInfoEntity.setMessage(Fail.getMessage());
		} finally {

			responseXML = CommonUtil.objectToGson(responseInfoEntity);
			logger.info("--线程(ID:" + Thread.currentThread().getId()
					+ ")，接口调用结束，响应报文如下：\n" + responseXML);
			// 向请求端发回反馈信息
			PrintWriter out = response.getWriter();
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			out.print(responseXML);
			out.flush();
			out.close();

			long end = System.currentTimeMillis();

			logger.debug("--线程(ID:" + Thread.currentThread().getId()
					+ ")，从接收‘请求数据’到‘业务处理’，最后‘返回响应数据’共耗时：" + (end - start)
					+ "毫秒---\n\n\n");
		}

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	private ResponseInfoEntity process (String requestXml, ApplicationContext ac) throws Exception {
		busiService = (IBusiService) ac.getBean("busiService");
			long start = System.currentTimeMillis();
			long end = System.currentTimeMillis();
			logger.info("--线程(ID:" + Thread.currentThread().getId()
					+ "),解析请求XML报文共耗时：" + (end - start) + "毫秒---\n");
			//1.接收非法版本协议
			final TmpBusiEntity tmpBusiEntity = (TmpBusiEntity) CommonUtil
					.gsonToObject(requestXml, TmpBusiEntity.class);
			
			ScheduleJobEntity scheduleJob =new  ScheduleJobEntity();
			//2.根据业务代码进行分类转协议
			if(tmpBusiEntity.getBusi().equals(BusiConstant.INNER_BUSI_TYPE_QUEUE)){
				
				//3.转Bean对象
				if(tmpBusiEntity.getOrder().equals(CzlxConstant.ORDER_CREATE_MQLISTENER)){
					//4.完成接口调用
					busiService.initMqListen();
				}else{
					/*--------业务不存在（即接口编码错误，系统不支持此接口）--------*/
	                logger.info("--线程(ID:" + Thread.currentThread().getId() + "),【非法业务编码】Begin"+ "\n\n\n");
	                responseInfoEntity.setCode(IllegalOrder.getCode());
	                responseInfoEntity.setMessage(IllegalOrder.getMessage());
	                logger.info("--线程(ID:" + Thread.currentThread().getId() + "),【非法业务编码】End"+ "\n\n\n");
				}
				
			}else if (tmpBusiEntity.getBusi().equals(BusiConstant.INNER_BUSI_TYPE_TASK)){
				//3.转Bean对象
				
				scheduleJob.setCronExpression(tmpBusiEntity.getCorn());
				scheduleJob.setSpringID(tmpBusiEntity.getSpringId());
				scheduleJob.setJobBusiType(tmpBusiEntity.getJobBusiType());
			
				switch (tmpBusiEntity.getOrder()) {
				case CzlxConstant.ORDER_CREATE_SCHEDULEJOB:
					scheduleJob.setCzlx(CzlxConstant.SCHEDULEJOB_CREATE);
					break;
				case CzlxConstant.ORDER_DELETE_SCHEDULEJOB:
					scheduleJob.setCzlx(CzlxConstant.SCHEDULEJOB_DELETE);
					break;
				case CzlxConstant.ORDER_UPDATE_SCHEDULEJOB:
					scheduleJob.setCzlx(CzlxConstant.SCHEDULEJOB_UPDATE);
					break;
				default:
					/*--------业务不存在（即接口编码错误，系统不支持此接口）--------*/
	                logger.info("--线程(ID:" + Thread.currentThread().getId() + "),【非法业务编码】Begin"+ "\n\n\n");
	                responseInfoEntity.setCode(IllegalOrder.getCode());
	                responseInfoEntity.setMessage(IllegalOrder.getMessage());
	                logger.info("--线程(ID:" + Thread.currentThread().getId() + "),【非法业务编码】End"+ "\n\n\n");
					break;
				}

				
			}else {
				/*--------业务不存在（即接口编码错误，系统不支持此接口）--------*/
                logger.info("--线程(ID:" + Thread.currentThread().getId() + "),【非法业务编码】Begin"+ "\n\n\n");
                responseInfoEntity.setCode(IllegalBusiness.getCode());
                responseInfoEntity.setMessage(IllegalBusiness.getMessage());
                logger.info("--线程(ID:" + Thread.currentThread().getId() + "),【非法业务编码】End"+ "\n\n\n");
			}
			
			return responseInfoEntity;

	}
}
