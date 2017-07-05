package com.aisino.domain.busi.dao.Impl;

import com.aisino.domain.busi.dao.IQueueDao;
import com.aisino.domain.protocol.bean.busi.QueueInfoEntity;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * DESCRIPTION：队列持久化实现类
 *
 * @author LuckyH
 * @create 2017-03-18 11:01
 **/
@Repository("queueDao")
public class QueueDao extends BaseDao implements IQueueDao{
    /**
     * 持久化单条队列信息
     *
     * @param queueInfoEntity
     */
    @Override
    public void saveQueue(QueueInfoEntity queueInfoEntity) throws SQLException{
        getSqlSessionTemplate().insert("insertQueueInfoEntity",queueInfoEntity);
    }

    /**
     * 持久化队列列表信息
     *
     * @param queueInfoEntityList
     */
    @Override
    public void saveQueue(List<QueueInfoEntity> queueInfoEntityList) throws SQLException{
        getSqlSessionTemplate().insert("insertQueueList",queueInfoEntityList);
    }

    /**
     * 查询所有队列数据
     */
    @Override
    public List<QueueInfoEntity>  queryAllQueue() throws SQLException{
        return getSqlSessionTemplate().selectList("selectQueueList");
    }

	@Override
	public String queryQueue(QueueInfoEntity queueInfoEntity) throws SQLException{
		return  getSqlSessionTemplate().selectOne("selectQueue", queueInfoEntity);
	}
}
