package com.aisino.domain.busi.service.impl;

import com.aisino.domain.busi.dao.IQueueDao;
import com.aisino.domain.busi.service.IQueueService;
import com.aisino.domain.protocol.bean.busi.QueueInfoEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service("queueService")
public class QueueServiceImpl implements IQueueService {

    @Autowired
    private IQueueDao queueDao;
    /**
     * Description:查询队列信息
     *
     * @return List<RabbitQueueEntity>队列实体列表
     * @throws SQLException 
     */
    @Override
    public List<QueueInfoEntity> getAllExistedQueues() throws SQLException {
        return queueDao.queryAllQueue();
    }

    @Override
    public void saveQueue(QueueInfoEntity queueInfoEntity) throws SQLException {

         queueDao.saveQueue(queueInfoEntity);

    }

    @Override
    public void saveQueueList(List<QueueInfoEntity> queueInfoEntityList) throws SQLException{
        queueDao.saveQueue(queueInfoEntityList);
    }

	@Override
	public String queryQueue(QueueInfoEntity queueInfoEntity) throws SQLException{
		// TODO Auto-generated method stub
		
		return queueDao.queryQueue(queueInfoEntity);
	}
}
