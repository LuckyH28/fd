package com.aisino.domain.busi.dao.Impl;

import com.aisino.domain.busi.dao.ITopicMsgDao;
import com.aisino.domain.protocol.bean.busi.TopicInfoEntity;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * DESCRIPTION：mqtt消息持久化实现类
 *
 * @author LuckyH
 * @create 2017-03-16 16:38
 **/
@Repository("topicMsgDao")
public class TopicMsgDao extends BaseDao implements ITopicMsgDao {
    /**
     * 持久化消息
     */
    @Override
    public void saveTopicMsg(TopicInfoEntity topicInfoEntity) throws SQLException{
        getSqlSessionTemplate().insert("insertTopicInfoEntity",topicInfoEntity);

    }

    /**
     * 删除消息
     */
    @Override
    public void deleteTopicMsg(TopicInfoEntity topicInfoEntity) throws SQLException{
        getSqlSessionTemplate().delete("deleteTopicInfoEntity",topicInfoEntity);
    }

    /**
     * 查询消息
     */
    @Override
    public List<TopicInfoEntity> queryTopicMsgs() throws SQLException{
        return getSqlSessionTemplate().selectList("selectTopicInfoEntityList");
    }

	@Override
	public List<TopicInfoEntity> queryTopicMsgsByDeviceNo(String deviceNo)
			throws SQLException {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("selectTopicInfoEntityListByDeviceNo",deviceNo);
	}
}
