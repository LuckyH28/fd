package com.aisino.domain.busi.service;

/**
 * DESCRIPTION：Redis业务层
 *
 * @author LuckyH
 * @create 2017-03-18 18:24
 **/
public interface IRedisService {

    void add(final String key, final String value);

    String getByKey(final String key);

    void setExpiryDate(final String key, final long seconds);

}
