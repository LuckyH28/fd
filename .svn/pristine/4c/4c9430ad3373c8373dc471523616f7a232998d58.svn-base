package com.aisino.domain.common.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Map;
import java.util.Properties;

/**
 * Created by LuckyH on 2017-03-09.
 */
public final class GlobalVariable {

    private static final Logger logger = LogManager.getLogger(GlobalVariable.class);

    private static Properties properties = new Properties();

    public static Map<String, String> map;

    //Topic相关
    /**
     * 服务消费者名称
     */
    public static String  consumer_name;
    /**
     * 服务生产者名称
     */
    public static String  service_name;
    
    public static String key_pref;
    

    //Mqtt相关
    public static  String host;
    public static  String clientId;
    public static  String userName;
    public static  String passWord ;

    public static int consumerSize;

   static {

        try {
            properties.load(GlobalVariable.class.getResourceAsStream("/general.properties"));

            //Topic相关
            consumer_name = properties.getProperty("consumer_name");
            service_name = properties.getProperty("service_name");
            key_pref = properties.getProperty("key_pref");
            
            //Mqtt相关
            host = properties.getProperty("mqtt.server.address");
            clientId = InetAddress.getLocalHost().getHostName();
//            clientId = properties.getProperty("mqtt.client.id");
//            String hostname = InetAddress.getLocalHost().getHostName();
            userName = properties.getProperty("mqtt.client.username");
            passWord = properties.getProperty("mqtt.client.password");

            consumerSize = Integer.parseInt(properties.getProperty("rabbitmq_listener_consumer")) ;

        } catch (IOException e) {
            logger.error("--线程(ID:" + Thread.currentThread().getId() +"读取系统属性配置文件出现异常，详情为:"+e.getMessage());
        }

    }
   /**
    * 字符编码
    */
    public static final String CHARSET = "UTF-8";
   
    /**
     * 队列层级，第一级
     */
    public static final String QUEUE_LEVEL_FIRST = "1";
    /**
     * 队列层级，第二级
     */
    public static final String QUEUE_LEVEL_SECOND = "2";
    
    /**
     * 压缩标志，已压缩
     */
    public static final String ZIPCODE_COMPRESSED="1";
    /**
     * 压缩标志，未压缩
     */
    public static final String ZIPCODE_UNCOMPRESSED="0";
    /**
     * 加密方式，不加密，仅Base64
     */
    public static final String ENCRYPT_BASE64="0";
    /**
     * 加密方式，3des加密
     */
    public static final String ENCRYPT_3DES="1";
    /**
     * 加密方式，CA加密
     */
    public static final String ENCRYPT_CA="2";
    
}
