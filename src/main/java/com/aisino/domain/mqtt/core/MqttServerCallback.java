package com.aisino.domain.mqtt.core;

import com.aisino.domain.task.RepublishTask;
import com.aisino.domain.zookeeper.core.LeaderDispatch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.aisino.domain.busi.service.IDeviceManagerService;
import com.aisino.domain.common.util.CommonUtil;
import com.aisino.domain.common.util.GlobalVariable;

import java.util.Date;

/**
 * DESCRIPTION：mqttserver回调函数
 *
 * @author LuckyH
 * @create 2017-03-18 14:03
 **/
@Service("mqttServerCallback")
public class MqttServerCallback implements MqttCallback {

    private static final Logger logger = LogManager.getLogger(MqttServerCallback.class);
    private String dateByFormat;

    @Autowired
    private IDeviceManagerService deviceManagerService;
    
    @Autowired
    private LeaderDispatch zkLeaderDispatch;
    
    @Autowired
    private RepublishTask republishTask;

    
    @Autowired
    private MqttServer mqttServer;
    /**
     * This method is called when the connection to the server is lost.
     *
     * @param cause the reason behind the loss of connection.
     */
    @Override
    public void connectionLost(Throwable cause) {
        dateByFormat = CommonUtil.getDateByFormat(new Date(),"yyyy/MM/dd HH:mm:ss");
        // 连接丢失后，一般在这里面进行重连
        logger.error("--线程(ID：" + Thread.currentThread().getId()+")，系统当前时间为："+dateByFormat+"，连接断开，可以做重连");
        
        try {
        	if(!deviceManagerService.isOnline(GlobalVariable.clientId)){
        		 logger.error("--线程(ID:" + Thread.currentThread().getId()+")，服务不在线，重新建立连接！");
        		mqttServer.doConnectToBroker();
        		mqttServer.subSysTopic();
        	}
            
        } catch (MqttException e) {
        	logger.error("--线程(ID:" + Thread.currentThread().getId()+")，系统与Broker重新建立连接失败，具体原因为:"+e);
        } catch (Exception e) {
			// TODO: handle exception
        	logger.error("--线程(ID:" + Thread.currentThread().getId()+")，系统获取设备状态信息失败，具体原因为:"+e);
		}
    }

    /**
     * This method is called when a message arrives from the server.
     * <p>
     * <p>
     * This method is invoked synchronously by the MQTT client. An
     * acknowledgment is not sent back to the server until this
     * method returns cleanly.</p>
     * <p>
     * If an implementation of this method throws an <code>Exception</code>, then the
     * client will be shut down.  When the client is next re-connected, any QoS
     * 1 or 2 messages will be redelivered by the server.</p>
     * <p>
     * Any additional messages which arrive while an
     * implementation of this method is running, will build up in memory, and
     * will then back up on the network.</p>
     * <p>
     * If an application needs to persist data, then it
     * should ensure the data is persisted prior to returning from this method, as
     * after returning from this method, the message is considered to have been
     * delivered, and will not be reproducible.</p>
     * <p>
     * It is possible to send a new message within an implementation of this callback
     * (for example, a response to this message), but the implementation must not
     * disconnect the client, as it will be impossible to send an acknowledgment for
     * the message being processed, and a deadlock will occur.</p>
     *
     * @param topic   name of the topic on the message was published to
     * @param message the actual message.
     * @throws Exception if a terminal error has occurred, and the client should be
     *                   shut down.
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        dateByFormat = CommonUtil.getDateByFormat(new Date(), "yyyy/MM/dd HH:mm:ss");

        String[] topicInfo = topic.split("/");
        //根据Topic名称判断是遗言还是回执，如遗言，则更新设备的在线状态为离线，如回调，可获取金税盘的可用状态
        //遗言
        if(topic.endsWith("disconnected")){
            if(zkLeaderDispatch.isLeader()){
                deviceManagerService.updateToOffline(topicInfo[4]);
                logger.info("--线程(ID：" + Thread.currentThread().getId()+").设备"+topicInfo[4]+"离线,离线原因为:"+message.toString());
            }


        }else if(topic.endsWith("connected")){
            if(zkLeaderDispatch.isLeader()){
                deviceManagerService.updateToOnline(topicInfo[4]);
                
                republishTask.execute(topicInfo[4]);
                
                logger.info("--线程(ID:" + Thread.currentThread().getId()+"). 设备"+topicInfo[4]+"上线,连接信息:"+message.toString());
            }
        }
        
        //根据Topic名称判断是遗言还是回执，如遗言，则更新设备的在线状态为离线，如回调，可获取金税盘的可用状态
        //遗言
       /* if(topic.startsWith("/will"))
        {
            deviceManagerService.updateToOffline(topic);
            System.out.println(dateByFormat+"will遗言消息主题 : " + topic);
            System.out.println(dateByFormat+"will遗言消息Qos : " + message.getQos());
            System.out.println(dateByFormat+"will遗言消息内容 : " + message.getPayload().toString());
        }
        //回调
        else if(topic.startsWith("/callback"))
        {
            deviceManagerService.updateToOnline(topic);
            System.out.println(dateByFormat+"callback回调消息主题 : " + topic);
            System.out.println(dateByFormat+"callback回调消息Qos : " + message.getQos());
            System.out.println(dateByFormat+"callback回调消息内容 : " + message.getPayload().toString());
        }*/

    }

    /**
     * Called when delivery for a message has been completed, and all
     * acknowledgments have been received. For QoS 0 messages it is
     * called once the message has been handed to the network for
     * delivery. For QoS 1 it is called when PUBACK is received and
     * for QoS 2 when PUBCOMP is received. The token will be the same
     * token as that returned when the message was published.
     *
     * @param token the delivery token associated with the message.
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        dateByFormat = CommonUtil.getDateByFormat(new Date(),"yyyy/MM/dd HH:mm:ss");
        System.out.println(dateByFormat+"deliveryComplete---------" + token.isComplete());
        logger.info("--线程(ID:" + Thread.currentThread().getId()+"). "+dateByFormat+"message deliveryComplete---------" + token.isComplete());
    }
}
