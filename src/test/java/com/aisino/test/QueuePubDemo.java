package com.aisino.test;

import java.util.TimerTask;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by LuckyH on 2017-03-09.
 */

public class QueuePubDemo {




    public static void main(String[] args) {

/*        ApplicationContext ac = new ClassPathXmlApplicationContext("/config.spring/rabbitmq.xml");

        QueuePubDemo bds = (QueuePubDemo) ac.getBean("bussinessDataService");*/
    	ApplicationContext ac = new ClassPathXmlApplicationContext("/config/spring/spring-context.xml");
    	TimerTask c = (TimerTask) ac.getBean("rabbitmqListener");
    	c.run();

    }
}
