package com.aisino.domain.rabbitmq.core;

import com.aisino.domain.protocol.bean.busi.QueueInfoEntity;
import com.rabbitmq.client.Channel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * DESCRIPTION：rabbitmq服务API
 *
 * @author LuckyH
 * @create 2017-03-16 10:57
 **/

@Service("rabbitmqServer")
public final class RabbitmqServer {

    private static final Logger logger = LogManager.getLogger (RabbitmqServer.class);

    @Autowired
    private ConnectionFactory connectionFactory;
    
    private static QueueInfoEntity queueInfoEntity = new QueueInfoEntity();

    private Connection connection = null;
    private Channel channel = null;
    private boolean flag = false;

    public  boolean createQueue(String queueName){
    	
        try {
            //添加zk锁控制队列的产生
            connection = connectionFactory.createConnection();
            //创建频道
            channel = connection.createChannel(true);
            //声明创建队列
            //判断该队列是否存在，此步骤进行并发控制，不容许出现两个相同的队列,创建队列为持久会队列
            channel.queueDeclarePassive(queueName);
            logger.info("--线程(ID:" + Thread.currentThread().getId()+"),"+queueName+"已存在，无需重复创建队列");
            flag = true;

        } catch (AmqpException e) {
            logger.error("--线程(ID:" + Thread.currentThread().getId()+"),添加队列"+queueName+"失败,原因"+e.getMessage());

        } catch (IOException e) {
        	 logger.info("--线程(ID:" + Thread.currentThread().getId()+"),"+queueName+"不存在，创建此队列");
        	 channel.queueDeclare(queueName, true, false, false, null);
        	 logger.info("--线程(ID:" + Thread.currentThread().getId()+"),添加队列"+queueName+"成功");
        	 flag = true;
        } 
        catch (Exception e) {
            logger.error("--线程(ID:" + Thread.currentThread().getId()+"),添加队列"+queueName+"失败,原因"+e.getMessage());

        }finally{
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.close();
            }

            return flag;
        }


    }

    public boolean deleteQueue(String queueName){

        try {
            //添加zk锁控制队列的产生
            connection = connectionFactory.createConnection();
            //创建频道
            channel = connection.createChannel(true);
            //删除一个队列
            channel.queueDelete(queueName);
            logger.info("--线程（ID：" + Thread.currentThread().getId()+"删除队列"+queueName+"成功");
            flag = true;
            //判断该队列是否存在
            //channel.queueDeclarePassive(queueName);
        } catch (AmqpException e) {
            logger.error("--线程（ID：" + Thread.currentThread().getId()+"删除队列"+queueName+"失败,原因"+e.getMessage());
        } catch (IOException e) {
            logger.error("--线程（ID：" + Thread.currentThread().getId()+"删除队列"+queueName+"失败,原因"+e.getMessage());
        }finally{
            if (channel==null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection==null) {
                connection.close();
            }
            return flag;
        }
    }
    
    
    public QueueInfoEntity  queueDeclare(String queueName){
    	
        //添加zk锁控制队列的产生
        connection = connectionFactory.createConnection();
        //创建频道
        channel = connection.createChannel(true);
        queueInfoEntity.setQueueName(queueName);
    	try {
			queueInfoEntity.setQueueConsumerCount(Integer.toString(channel.queueDeclarePassive(queueName).getConsumerCount()));
			queueInfoEntity.setQueueMessageCount(Integer.toString(channel.queueDeclarePassive(queueName).getMessageCount()));
		} catch (IOException e) {
       	 
       	 logger.error("--线程(ID:" + Thread.currentThread().getId()+"),队列"+queueName+"不存在，请确认后再查询！");
       	
       } catch (Exception e){
    	   logger.error("--线程(ID:" + Thread.currentThread().getId()+"),队列"+queueName+"不存在，请确认后再查询！");
       }finally{
    	   return queueInfoEntity;
       }
    	
    }

}
