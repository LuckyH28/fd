package com.aisino.domain.busi.service;

import java.sql.SQLException;

import org.eclipse.paho.client.mqttv3.MqttException;

import com.aisino.domain.protocol.bean.busi.QueueInfoEntity;
import com.aisino.domain.protocol.bean.busi.ResponseInfoEntity;
import com.aisino.domain.protocol.bean.busi.ScheduleJobEntity;

/**
 * Description：业务处理服务接口类
 *
 * @author LuckyH
 * @create 2017-03-16 10:30
 **/
public interface IBusiService {

    /**
     * Description:查询设备信息
     * @return
     */
    ResponseInfoEntity queryDeviceInfo();

    /**
     * Description:发布主题
     * @return
     */
    ResponseInfoEntity publicTopic();

    /**
     * Description:订阅主题
     * @return
     */
    ResponseInfoEntity subscribeTopic();
    /**
     * Description:取消订阅
     * @return
     */
    ResponseInfoEntity unsubscribeTopic();
    
    
    /**
     * 初始化队列监听
     * @throws Exception
     */
    void initMqListen()throws Exception;
    


    /**
     * 初始化Mqtt服务
     * @throws Exception 
     * @throws MqttException 
     */
    void initMqttServer() throws Exception;

    /**
     *
     *
     * @return
     */
    ResponseInfoEntity initQueue(QueueInfoEntity queueInfoEntity)throws Exception;

}
