package com.aisino.domain.busi.service.impl;

import com.aisino.domain.busi.service.IRedisService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * DESCRIPTION：redis业务操作实现层
 *
 * @author LuckyH
 * @create 2017-03-20 13:35
 **/

@Service("redisService")
public class RedisServiceImpl implements IRedisService {
    private static final Logger logger = LogManager.getLogger(RedisServiceImpl.class);

    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Override
    public void add(String key, String value) {
        redisTemplate.boundValueOps(key).set(value);
    }

    @Override
    public String getByKey(String key) {
        return redisTemplate.boundValueOps(key).get();
    }

    @Override
    public void setExpiryDate(String key, long seconds) {
        redisTemplate.expire(key,seconds, TimeUnit.SECONDS);
    }
    
}
