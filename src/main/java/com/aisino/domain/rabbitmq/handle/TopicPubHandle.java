package com.aisino.domain.rabbitmq.handle;

import java.sql.SQLException;

import com.aisino.domain.busi.service.IDeviceManagerService;
import com.aisino.domain.busi.service.IMqttService;
import com.aisino.domain.busi.service.ITopicMsgService;
import com.aisino.domain.common.util.CommonUtil;
import com.aisino.domain.protocol.bean.busi.TopicInfoEntity;
import com.aisino.rabbitPlugin.listener.BaseListener;
import com.google.gson.JsonSyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by LuckyH on 2017-03-11.
 * <p>
 * 用于处理订单的请求，获取订单后发送消息通知
 */
@Component(value = "topicPubHandle")
public class TopicPubHandle implements BaseListener {

    private static final Logger logger = LogManager.getLogger(TopicPubHandle.class);
    @Autowired
    private IMqttService mqttService;
    @Autowired
    private IDeviceManagerService deviceManagerService;
    @Autowired
    private ITopicMsgService topicMsgService;
    
    private static TopicInfoEntity topicInfoEntity = new TopicInfoEntity();

    /**
     * 订单消息通知，监听队列方法，完成订单的消息通知
     *
     * @param message 队列消息内容
     * @throws MqttException
     */
    @Override
    public void onMessage(Message message) {
    	 logger.debug("--线程(ID:" + Thread.currentThread().getId() + "),监听数据为：" + message);
    	
    	try {
    		topicInfoEntity = (TopicInfoEntity) CommonUtil.gsonToObject(new String(message.getBody()), TopicInfoEntity.class);
    		  //判断D1的在线状态，如在线则继续进行，如不在线则直接返回（直接返回至队列，需考虑如何处理）
            boolean is_online = deviceManagerService.isOnline(topicInfoEntity.getDeviceNo());
            if (is_online) {
                logger.debug("--线程(ID:" + Thread.currentThread().getId() + "),设备" + topicInfoEntity.getDeviceNo() + "在线，消息主题topic为：" + topicInfoEntity.getTopic());
                mqttService.pubMsg(topicInfoEntity.getTopic(), topicInfoEntity.getMessage());
            } else {
                try {
    				topicMsgService.saveTopicMsg(topicInfoEntity);
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				logger.error("--线程(ID:" + Thread.currentThread().getId() + "),sql异常：" + e);
    			}
                logger.info("--线程(ID:" + Thread.currentThread().getId() + "),设备" + topicInfoEntity.getDeviceNo() + "离线，待设备上线后重新发布消息"+topicInfoEntity.getId());
            }
    	}catch (JsonSyntaxException e){
    		 logger.error("--线程(ID:" + Thread.currentThread().getId() + "),队列数据格式不规范：" + e);
    	}catch(SQLException e){
    		e.printStackTrace();
    	}catch(MqttException e){
    		 try {
    		 //消息持久化
            topicMsgService.saveTopicMsg(topicInfoEntity);
            logger.error("--线程(ID:" + Thread.currentThread().getId() + "),本服务与Broker失去连接，正进行重连，本次消息发布业务操作失败，失败原因为：" + e);
           
				mqttService.doConnect();
			}catch (MqttException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
            
    	}catch(Exception e){
    		logger.error("--线程(ID:" + Thread.currentThread().getId() + "),本服务与Broker失去连接，重连失败，失败详情为：" + e);
            
    	}
    	
    	
    	
       
        
		
		try {
			
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
      
        
    }
}
