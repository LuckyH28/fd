package com.aisino.domain.busi.dao;

import com.aisino.domain.protocol.bean.busi.QueueInfoEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * DESCRIPTION：队列信息持久化接口
 *
 * @author LuckyH
 * @create 2017-03-18 10:38
 **/
public interface IQueueDao {

    /**
     * 持久化单条队列信息
     * @param queueInfoEntity
     * @throws SQLException 
     */
    void saveQueue(QueueInfoEntity queueInfoEntity) throws SQLException;

    /**
     * 持久化队列列表信息
     * @param queueInfoEntityList
     */
    void saveQueue(List<QueueInfoEntity> queueInfoEntityList) throws SQLException;

    /**
     * 查询所有队列数据
     */
    List<QueueInfoEntity>  queryAllQueue() throws SQLException;
    
    /**
     * 查询某队列数据详情
     */
    String  queryQueue(QueueInfoEntity queueInfoEntity) throws SQLException;

}
