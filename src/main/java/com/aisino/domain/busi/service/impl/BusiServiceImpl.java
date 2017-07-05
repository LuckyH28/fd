package com.aisino.domain.busi.service.impl;


import static com.aisino.domain.common.enums.ReturnCodeEnum.*;
import static com.aisino.domain.common.enums.ScheduleJobBusiEnum.ScheduleJob_Republic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aisino.domain.busi.service.IBusiService;
import com.aisino.domain.busi.service.IMqttService;
import com.aisino.domain.busi.service.IQueueService;
import com.aisino.domain.common.enums.CzlxConstant;
import com.aisino.domain.protocol.bean.busi.QueueInfoEntity;
import com.aisino.domain.protocol.bean.busi.ResponseInfoEntity;
import com.aisino.domain.protocol.bean.busi.ScheduleJobEntity;
import com.aisino.domain.rabbitmq.core.RabbitmqListener;
import com.aisino.domain.rabbitmq.core.RabbitmqServer;

/**
 * DESCRIPTION：业务处理服务接口实现类
 *
 * @author LuckyH
 * @create 2017-03-16 10:30
 **/
@Service("busiService")
public class BusiServiceImpl implements IBusiService {

	private static final Logger logger = LogManager
			.getLogger(BusiServiceImpl.class);


	@Autowired
	private IQueueService queueService;

	@Autowired
	private RabbitmqListener rabbitmqListener;

	@Autowired
	private RabbitmqServer rabbitmqServer;

	@Autowired
	private IMqttService  mqttService;

	private ResponseInfoEntity responseInfoEntity = new ResponseInfoEntity();

	/**
	 * Description:查询设备信息
	 *
	 * @return
	 */
	@Override
	public ResponseInfoEntity queryDeviceInfo() {
		return null;
	}

	/**
	 * Description:发布主题
	 *
	 * @return
	 */
	@Override
	public ResponseInfoEntity publicTopic() {
		return null;
	}

	/**
	 * Description:订阅主题
	 *
	 * @return
	 */
	@Override
	public ResponseInfoEntity subscribeTopic() {
		return null;
	}

	/**
	 * Description:取消订阅
	 *
	 * @return
	 */
	@Override
	public ResponseInfoEntity unsubscribeTopic() {
		return null;
	}
	
	/**
	 * Description:初始化队列
	 *
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResponseInfoEntity initQueue(QueueInfoEntity queueInfoEntity)
			throws Exception {

		if (queueInfoEntity.getQueueBusiType().equalsIgnoreCase("")) {

			if (queueInfoEntity.getQueueName().equals("")
					|| queueInfoEntity.getQueueName() == null) {

				queueInfoEntity.setQueueName(
						  queueInfoEntity.getQueueService()+"_"
						+ queueInfoEntity.getQueueConsumer()+"_"
						+ queueInfoEntity.getQueueBusiType()+"_"
						+ queueInfoEntity.getQueueNO()+"_"
						+ queueInfoEntity.getQueueLevel());

			} else {

			}

		} else {
			logger.error("--线程(ID:" + Thread.currentThread().getId()
					+ ").非法业务类型");
			responseInfoEntity.setCode("9999");
			responseInfoEntity.setMessage("非法业务类型!");
			return responseInfoEntity;
		}

		try {
			switch (queueInfoEntity.getCzlx()) {
			case CzlxConstant.QUEUE_CREATE:
				rabbitmqServer.createQueue(queueInfoEntity.getQueueName());
				queueService.saveQueue(queueInfoEntity);
				break;
			case CzlxConstant.QUEUELISTENER_CREATE:
				rabbitmqListener.startupListener(queueInfoEntity);
				break;
			default:
				responseInfoEntity.setCode(IllegalCzlx.getCode());
				responseInfoEntity.setMessage(IllegalCzlx.getMessage());
				break;
			}
			responseInfoEntity.setCode(Success.getCode());
			responseInfoEntity.setMessage(Success.getMessage());
		} catch (Exception e) {
			logger.error("--线程(ID:" + Thread.currentThread().getId()
					+ ").定时器创建失败,详情为:" + e);
			responseInfoEntity.setCode("9999");
			responseInfoEntity.setMessage("队列操作失败!");
		}
		return responseInfoEntity;
	}



	@Override
	public void initMqListen() throws Exception {
		// TODO Auto-generated method stub
		rabbitmqListener.run();
	}



	@Override
	public void initMqttServer() throws Exception {
		// TODO Auto-generated method stub
		mqttService.init();
	}

}
