package com.aisino.domain.Servlet;

import com.aisino.domain.busi.service.IBusiService;

import static com.aisino.domain.common.enums.ReturnCodeEnum.RequestIsEmpty;

import com.aisino.domain.common.enums.BusiConstant;
import com.aisino.domain.common.util.CommonUtil;
import com.aisino.domain.protocol.bean.busi.QueueInfoEntity;
import com.aisino.domain.protocol.bean.busi.RequestBusiEntity;
import com.aisino.domain.protocol.bean.busi.ResponseInfoEntity;
import com.aisino.domain.protocol.bean.busi.ScheduleJobEntity;
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
public class InnerEntranceServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1891622684270253336L;

	private static final Logger logger = LogManager.getLogger(InnerEntranceServlet.class.getName());

    private String dateByFormat;
    private IBusiService busiService;
    
    private static ResponseInfoEntity responseInfoEntity = new ResponseInfoEntity();

    /**
     * EntranceServlet初始化
     * @throws ServletException
     */
    public void init() throws ServletException {
    	
    }

    /**
     * EntranceServlet注销
     */
    public void destroy() {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String responseXML = "";
    	dateByFormat = CommonUtil.getDateByFormat(new Date(),"yyyy/MM/dd HH:mm:ss");
        long start = System.currentTimeMillis();
    	try{
    		
    		
            //获取请求报文,并设置编码方式
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            ServletInputStream inputStream = request.getInputStream();
            String requestXML = IOUtils.toString(inputStream,"UTF-8");
            IOUtils.closeQuietly(inputStream);

            
            if(!StringUtils.isEmpty(requestXML)){
                logger.info("--线程(ID:" + Thread.currentThread().getId()+")，接口调用开始，请求报文如下:\n" + requestXML);
                ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
                //处理请求，返回响应报文
                	responseInfoEntity = process(requestXML,ac);
            }else{
            	 responseInfoEntity.setCode(RequestIsEmpty.getCode());
                 responseInfoEntity.setMessage(RequestIsEmpty.getMessage());
            }
    		
    	}catch(Exception e){
    		
    	}finally{
    		
    		 responseXML = CommonUtil.objectToGson(responseInfoEntity);
        	 logger.info("--线程(ID:" + Thread.currentThread().getId()+")，接口调用结束，响应报文如下：\n" + responseXML);
    		  //向请求端发回反馈信息
            PrintWriter out = response.getWriter();
            response.setCharacterEncoding("utf-8");        
            response.setContentType("text/html; charset=utf-8"); 
            out.print(responseXML);
            out.flush();
            out.close();

            long end = System.currentTimeMillis();

            logger.debug("--线程(ID:" + Thread.currentThread().getId()+")，从接收‘请求数据’到‘业务处理’，最后‘返回响应数据’共耗时：" + (end-start) + "毫秒---\n\n\n");
    	}
    	
    	
    	
    	
       
      

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }


    private ResponseInfoEntity process(String requestXml,ApplicationContext ac){

        busiService = (IBusiService) ac.getBean("busiService");
        try {
            long start = System.currentTimeMillis();
            long end = System.currentTimeMillis();
            logger.info("--线程(ID:" + Thread.currentThread().getId() + "),解析请求XML报文共耗时：" + (end-start) + "毫秒---\n");

            final RequestBusiEntity busiEntity = (RequestBusiEntity) CommonUtil.gsonToObject(requestXml, RequestBusiEntity.class);

            String content = CommonUtil.decode(busiEntity.getContent(),busiEntity.getEncrypt());
            /**
             * 根据busiType处理不同业务
             */
            switch (busiEntity.getBusiType()) {
			case BusiConstant.BUSI_TYPE_QUERY_DEVICE_STATUS:
				/*-------设备状态查询--------*/
				 logger.info("--线程(ID:" + Thread.currentThread().getId() + "),【设备状态查询】Begin"+ "\n\n\n");
	                responseInfoEntity =  busiService.queryDeviceInfo();
	                logger.info("--线程(ID:" + Thread.currentThread().getId() + "),【设备状态查询】End"+ "\n\n\n");
				break;
			case BusiConstant.BUSI_TYPE_INIT_QUEUE:
				/*-------队列管理--------*/
                logger.info("--线程(ID:" + Thread.currentThread().getId() + "),【队列管理】Begin"+ "\n\n\n");
                final QueueInfoEntity queueInfoEntity = (QueueInfoEntity) CommonUtil.gsonToObject(busiEntity.getContent(), QueueInfoEntity.class);
                responseInfoEntity =  busiService.initQueue(queueInfoEntity);
                logger.info("--线程(ID:" + Thread.currentThread().getId() + "),，【队列管理】End"+ "\n\n\n");
				break;
				
			case BusiConstant.BUSI_TYPE_PUBLIC_MESSAGE:
				/*-------消息发布--------*/
                logger.info("--线程(ID:" + Thread.currentThread().getId() + "),【消息发布】Begin"+ "\n\n\n");
                responseInfoEntity = busiService.publicTopic();
                logger.info("--线程(ID:" + Thread.currentThread().getId() + "),【消息发布】End"+ "\n\n\n");
				break;
			case BusiConstant.BUSI_TYPE_SUBSCRIBE_TOPIC:
				/*-----------消息订阅------------*/
                logger.info("--线程(ID:" + Thread.currentThread().getId() + "),【消息订阅】Begin"+ "\n\n\n");
                responseInfoEntity =  busiService.initQueue(null);
                logger.info("--线程(ID:" + Thread.currentThread().getId() + "),【消息订阅】End"+ "\n\n\n");
				break;
			case BusiConstant.BUSI_TYPE_UNSUBSCRIBE_TOPIC:
				/*-------取消消息订阅--------*/
                logger.info("--线程(ID:" + Thread.currentThread().getId() + "),【取消消息订阅】Begin"+ "\n\n\n");
               responseInfoEntity =  busiService.initQueue(null);
                logger.info("--线程(ID:" + Thread.currentThread().getId() + "),【取消消息订阅】End"+ "\n\n\n");
				break;
			
			default:
				/*--------业务不存在（即接口编码错误，系统不支持此接口）--------*/
                logger.info("--线程(ID:" + Thread.currentThread().getId() + "),【非法业务编码】Begin"+ "\n\n\n");
                responseInfoEntity.setCode("9999");
                responseInfoEntity.setMessage("非法业务类型！");
                logger.info("--线程(ID:" + Thread.currentThread().getId() + "),【非法业务编码】End"+ "\n\n\n");
				break;
			}
            

        } catch (JsonSyntaxException jsonException){	// 发票数据不合法
            logger.error("--线程(ID:" + Thread.currentThread().getId() + "),业务处理发生异常，详情为：------------------------------"+jsonException);
            responseInfoEntity.setCode("9999");
            responseInfoEntity.setMessage("报文格式不规范！");

        } catch (Exception e){
        	  logger.error("--线程(ID:" + Thread.currentThread().getId() + "),业务处理发生异常，详情为：------------------------------"+e);
        	  responseInfoEntity.setCode("9999");
              responseInfoEntity.setMessage("其它异常！");
        } finally {
            return responseInfoEntity;
        }

    }
}
