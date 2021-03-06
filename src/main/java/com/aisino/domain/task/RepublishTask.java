package com.aisino.domain.task;

import com.aisino.domain.busi.service.IDeviceManagerService;
import com.aisino.domain.busi.service.IQueueService;
import com.aisino.domain.busi.service.ITopicMsgService;
import com.aisino.domain.common.util.GlobalVariable;
import com.aisino.domain.protocol.bean.busi.QueueInfoEntity;
import com.aisino.domain.protocol.bean.busi.TopicInfoEntity;

import com.aisino.domain.zookeeper.core.LeaderDispatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DESCRIPTION：RepublishTopicMessage
 *
 * @author LuckyH
 * @create 2017-03-23 17:55
 **/
@Service("republishTask")
public class RepublishTask {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private ITopicMsgService topicMsgService;

    @Autowired
    private IDeviceManagerService deviceMangerService;
	@Autowired
	private LeaderDispatch zkLeaderDispatch;
    @Autowired
    private IQueueService queueService;
    private static QueueInfoEntity queueInfoEntity = new QueueInfoEntity();

    private static final Logger logger = LogManager.getLogger(RepublishTask.class);
    
    public void execute(){

    	try {
			if(zkLeaderDispatch.isLeader()){
				logger.info("--线程(ID:" + Thread.currentThread().getId()+").反补发布消息开始.......");
				List<TopicInfoEntity> topicInfoEntityList = topicMsgService.queryTopicMsgs();
				if(topicInfoEntityList.size()>0){
					queueInfoEntity.setQueueLevel(GlobalVariable.QUEUE_LEVEL_FIRST);
					queueInfoEntity.setQueueService(GlobalVariable.service_name);
					queueInfoEntity.setQueueConsumer(GlobalVariable.consumer_name);
					queueInfoEntity.setQueueBusiType("");

					String queueName = queueService.queryQueue(queueInfoEntity);
					for (TopicInfoEntity topicInfoEntity:topicInfoEntityList)
					{
						if (deviceMangerService.isOnline(topicInfoEntity.getDeviceNo())){
							amqpTemplate.convertAndSend(queueName,topicInfoEntity);
							topicMsgService.deleteTopicMsg(topicInfoEntity);
						}
					}

				}
			}

    	} catch (Exception e){
			logger.error("--线程(ID:" + Thread.currentThread().getId()+").反补发布消息失败，详情为："+e);
		}

    }
    
    public void execute(String deviceNo){
    	
    	try {
		        logger.info("--线程(ID:" + Thread.currentThread().getId()+").反补发布消息开始.......");
		        List<TopicInfoEntity> topicInfoEntityList = topicMsgService.queryTopicMsgsByDeviceNo(deviceNo);
		        if(topicInfoEntityList.size()>0){
		        	 queueInfoEntity.setQueueLevel(GlobalVariable.QUEUE_LEVEL_FIRST);
		             queueInfoEntity.setQueueService(GlobalVariable.service_name);
		             queueInfoEntity.setQueueConsumer(GlobalVariable.consumer_name);
		             queueInfoEntity.setQueueBusiType("");
		     		
	     		String queueName = queueService.queryQueue(queueInfoEntity);
	 			for (TopicInfoEntity topicInfoEntity:topicInfoEntityList) 
	 			{
 		            if (deviceMangerService.isOnline(topicInfoEntity.getDeviceNo())){
 		                amqpTemplate.convertAndSend(queueName,topicInfoEntity);
 		               topicMsgService.deleteTopicMsg(topicInfoEntity);
 		            }
	 		     }
	     		
	        }
    	} catch (Exception e) {
 			e.printStackTrace();
 		}

    }
    
    
    public static void main(String[] args) {
		ApplicationContext content = new ClassPathXmlApplicationContext("classpath*:/config/spring/spring-context.xml");
		RepublishTask republishTask = (RepublishTask) content.getBean("republishTask") ;
		republishTask.execute();
		
	}
    


}
