package com.aisino.domain.rabbitmq.core;

import com.aisino.domain.busi.service.IQueueService;
import com.aisino.domain.common.util.GlobalVariable;
import com.aisino.domain.protocol.bean.busi.QueueInfoEntity;
import com.aisino.rabbitPlugin.core.LisenerDeclare;
import com.aisino.rabbitPlugin.core.LisenerFactory;
import com.aisino.rabbitPlugin.listener.BaseListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * DESCRIPTION：rabbitmq队列监听
 *
 * @author LuckyH
 * @create 2017-03-17 13:48
 **/
@Component("rabbitmqListener")
public class RabbitmqListener extends TimerTask {

    private static final Logger logger = LogManager.getLogger (RabbitmqListener.class);

    private static List<String> LISTENERS = new ArrayList<String>();
    @Autowired
    private ConnectionFactory ConnectionFactory;
    @Autowired
    private IQueueService queueService;
    @Autowired
    private BaseListener topicPubHandle;
    @Autowired
    private BaseListener topicRepubHandle;
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    private static LisenerDeclare listener;
    
    public RabbitmqListener(){
//    	run();
    }

    private void initListener(QueueInfoEntity queue ,int consumerSize ,BaseListener baseListener){
        List<String> queueNameList = new ArrayList<String>();
        queueNameList.add(queue.getQueueName());
        listener = new LisenerDeclare(ConnectionFactory, queueNameList, consumerSize, baseListener);
    }

    public void startupListener(QueueInfoEntity queue){
    	try {
//		    List<QueueInfoEntity> queueList = queueService.getAllExistedQueues();
		    LisenerFactory factory = LisenerFactory.instantiation();
		    Map<String,SimpleMessageListenerContainer> map = factory.getAllLiseners();
		    if(map == null || map.get(factory.getLisenerMapKey(ConnectionFactory,queue.getQueueName())) == null){
		        if (queue.getQueueLevel().equals(GlobalVariable.QUEUE_LEVEL_FIRST)){
		            initListener(queue, GlobalVariable.consumerSize,topicPubHandle);
		        }else if (queue.getQueueLevel().equals(GlobalVariable.QUEUE_LEVEL_SECOND)){
		            initListener(queue, GlobalVariable.consumerSize,topicRepubHandle);
		        }
		    boolean b = listener.declareLisener(taskExecutor);
		    logger.info("--线程(ID:" + Thread.currentThread().getId()+"),队列:"+ queue.getQueueName()+"启动监听,结果:"+b);
        }else {
            logger.info("--线程(ID:" + Thread.currentThread().getId()+"),队列:"+ queue.getQueueName()+"监听已存在，无须重复启动");
        }
    	} catch (Exception e) {
            logger.error("--线程(ID:" + Thread.currentThread().getId()+"),队列为:"+queue.getQueueName()+"的监听启动失败.详情为:"+e);
        }
    }


    @Override
    public void run() {
    	
    	try{
    		 List<QueueInfoEntity> queueInfoEntityList = queueService.getAllExistedQueues();
    	        if(queueInfoEntityList.size()>0) {
    	            //java 1.8 新特性
    	            queueInfoEntityList.forEach(this::startupListener);
    	        }
    	        else{
    	            logger.info("--线程(ID:" + Thread.currentThread().getId()+"),无队列需创建监听");
    	        }
//    	        for (QueueInfoEntity queue:queueInfoEntityList
//    	             ) {
//    	            startupListener(queue);
//    	        }
    	}catch(Exception e){
    		logger.error("--线程(ID:" + Thread.currentThread().getId()+"),队列监听启动失败,错误详情为："+e);
    	}
       

    }
    public void deleteListener(){
        LisenerFactory.instantiation().getAllLiseners().clear();
        logger.info("--线程(ID:" + Thread.currentThread().getId()+"),清空队列总数："+LisenerFactory.instantiation().getAllLiseners().size()+"条");
    }

}
