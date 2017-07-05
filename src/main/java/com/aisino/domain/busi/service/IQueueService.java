package com.aisino.domain.busi.service;

import com.aisino.domain.protocol.bean.busi.QueueInfoEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by LuckyH on 2017-03-16.
 */
public interface IQueueService {



    /**
     * Description:查询队列信息
     * @return List<RabbitQueueEntity>队列实体列表
     */
    List<QueueInfoEntity> getAllExistedQueues()throws SQLException;

    void saveQueue(QueueInfoEntity queueInfoEntity)throws SQLException;

    void saveQueueList(List<QueueInfoEntity> queueInfoEntityList)throws SQLException;

    String queryQueue(QueueInfoEntity queueInfoEntity)throws SQLException;

}
