package com.aisino.domain.mqtt.core;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import com.aisino.domain.common.util.GlobalVariable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LuckyH on 2017-03-08.
 *
 * Mqtt客户端完成订阅发布等相关功能api public MqttServer()throws MqttException完成Mqtt客户端的初始化
 * public static void doConnectToServer()完成与服务端的连接建立 public static void
 * pubMessage完成相关主题的消息发布 public static void subTopic完成客户端主题订阅
 *
 */
@Service("mqttServer")
public final class MqttServer {

	private static final Logger logger = LogManager.getLogger(MqttServer.class);

	private static MqttClient client;
	private static MqttTopic topic;
	private static MqttMessage message;
	private static String[] sysTopic;
	private static int[] sysQos;
	@Autowired
	private MqttServerCallback mqttServerCallback;

	/*
	 * @Autowired private static MqttServerCallback mqttServerCallback;
	 */

	static {
		try {

			sysTopic = new String[] { "$SYS/brokers/+/clients/+/connected",
					"$SYS/brokers/+/clients/+/disconnected" };
			sysQos = new int[] { 1, 1 };
			
			client = new MqttClient(GlobalVariable.host,
					GlobalVariable.clientId, new MemoryPersistence());
			message = new MqttMessage();

		} catch (Exception  e) {
			logger.error("--线程(ID:" + Thread.currentThread().getId()
					+ "),MqttServer初始化失败，异常信息为:" + e);
		}
	}

	/*
	 * public MqttServer() { // MemoryPersistence设置clientid的保存形式，默认以内容保存 try {
	 * 
	 * //初始化消息和客户端 client = new MqttClient(GlobalVariable.host,
	 * GlobalVariable.clientId, new MemoryPersistence());
	 * System.out.println(client); message = new MqttMessage(); //设置客户端回调函数
	 * client.setCallback(mqttServerCallback); //与Broker初始建立连接
	 * doConnectToBroker(); //订阅系统主题 subTopic(sysTopic,sysQos);
	 * logger.info("--线程（ID：" + Thread.currentThread().getId()
	 * +"MqttServer初始化成功，完成Broker的连接建立及系统主题订阅"); } catch (MqttException e) {
	 * logger.error("--线程（ID：" + Thread.currentThread().getId()
	 * +"MqttServer初始化失败，异常信息为："+e); }
	 * 
	 * }
	 */

	public void doConnectToBroker() throws MqttException {
		// 设置mqtt连接参数
		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(false);
		options.setUserName(GlobalVariable.userName);
		options.setPassword(GlobalVariable.passWord.toCharArray());
		options.setConnectionTimeout(200);
		options.setKeepAliveInterval(20);
		options.setAutomaticReconnect(true);
		client.setCallback(mqttServerCallback);
		// 建立连接
		client.connect(options);
		logger.info("--线程(ID：" + Thread.currentThread().getId()
				+ "),连接MQTT Broker"+GlobalVariable.host+"成功！");
	}

	public void pubMessage(String busiTopic, String busiMsg)
			throws MqttException {
		// 根据设置的主题名称获得topic
		topic = client.getTopic(busiTopic);

		message.setQos(2);
//		message.setRetained(true);
		message.setRetained(false);

		message.setPayload(busiMsg.getBytes());
		MqttDeliveryToken token = topic.publish(message);
		token.waitForCompletion();
		logger.info("--线程(ID：" + Thread.currentThread().getId()
				+ "),主题为"+busiTopic+"的消息"+ busiMsg+"发布成功!");

	}

	/**
	 *
	 * @param topicFilter
	 * @param qos
	 * @throws MqttException
	 */
	public void subTopic(String[] topicFilter, int[] qos) throws MqttException {
		client.subscribe(topicFilter, qos);
		logger.info("--线程(ID：" + Thread.currentThread().getId() + "),成功订阅的主题："
				+ topicFilter.toString());
	}

	/**
	 *
	 * @param topicFilter
	 * @throws MqttException
	 */
	public void subTopic(String[] topicFilter) throws MqttException {
		client.subscribe(topicFilter);
		logger.info("--线程(ID：" + Thread.currentThread().getId() + "),成功订阅的主题："
				+ topicFilter.toString());
	}

	/**
	 *
	 * @param topicFilter
	 * @throws MqttException
	 */
	public void subTopic(String topicFilter) throws MqttException {
		client.subscribe(topicFilter);
		logger.info("--线程(ID：" + Thread.currentThread().getId() + "),成功订阅的主题："
				+ topicFilter.toString());
	}

	/**
	 *
	 * @param topicFilter
	 * @param qos
	 * @throws MqttException
	 */
	public void subTopic(String topicFilter, int qos) throws MqttException {
		client.subscribe(topicFilter, qos);
		logger.info("--线程(ID：" + Thread.currentThread().getId() + "),成功订阅的主题:"
				+ topicFilter.toString());
	}
	
	public void subSysTopic() throws MqttException{
		logger.info("--线程(ID：" + Thread.currentThread().getId() + "),订阅系統主题前:"
				+ Arrays.toString(sysTopic)+",系统主题QOS为:"+Arrays.toString(sysQos));
		client.subscribe(sysTopic, sysQos);
		logger.info("--线程(ID：" + Thread.currentThread().getId() + "),成功订阅系統主题:"
				+ Arrays.toString(sysTopic)+",系统主题QOS为:"+Arrays.toString(sysQos));
	}
	
	public static void main(String[] args) {
		int[] sysTopic = new int[] { 1, 1 };
		System.out.println(Arrays.toString(sysTopic));
	}

}
