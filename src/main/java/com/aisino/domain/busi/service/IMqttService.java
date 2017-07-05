package com.aisino.domain.busi.service;

import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Created by LuckyH on 2017-03-04.
 *
 * * 实现mqtt消息推送的功能，包括建立连接、订阅主题、发布消息
 *
 */
public interface IMqttService {

    /**
     * 发布消息
     * @param topic 消息主题
     * @param message 消息内容
     * @throws MqttException 异常
     */
    void pubMsg(String topic, String message) throws MqttException;


    /**
     * 订阅主题
     * @param topic 订阅的主题名称
     * @throws MqttException 异常
     */
    void subTopic(String topic) throws MqttException;

    /**
     * 建立长连接
     * @throws MqttException
     * @throws Exception 
     */
    void doConnect() throws Exception;
    
    /**
     * 初始化Mqtt连接
     * @throws MqttException
     * @throws Exception
     */
    void init() throws Exception;

}
