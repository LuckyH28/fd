package com.aisino.domain.busi.service.impl;

import com.aisino.domain.busi.dao.ITopicMsgDao;
import com.aisino.domain.busi.service.ITopicMsgService;
import com.aisino.domain.protocol.bean.busi.TopicInfoEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DESCRIPTION：主题消息服务实现类
 *
 * @author LuckyH
 * @create 2017-03-18 11:18
 **/
@Service("topicMsgService")
public class TopicMsgServiceImpl implements ITopicMsgService  {

    @Autowired
    private ITopicMsgDao topicMsgDao;
    /**
     * 持久化未发布消息
     *
     * @param topicInfoEntity
     */
    @Override
    public void saveTopicMsg(TopicInfoEntity topicInfoEntity) throws Exception{
        topicMsgDao.saveTopicMsg(topicInfoEntity);
    }

    /**
     * 未发布消息成功发布后进行去持久化
     *
     * @param topicInfoEntity
     */
    @Override
    public void deleteTopicMsg(TopicInfoEntity topicInfoEntity)  throws Exception{
        topicMsgDao.deleteTopicMsg(topicInfoEntity);

    }

    /**
     * 查询未发布成功的topic消息
     *
     * @return
     */
    @Override
    public List<TopicInfoEntity> queryTopicMsgs()  throws Exception{
        return topicMsgDao.queryTopicMsgs();
    }

	@Override
	public List<TopicInfoEntity> queryTopicMsgsByDeviceNo(String deviceNo)
			throws Exception {
		// TODO Auto-generated method stub
		return topicMsgDao.queryTopicMsgsByDeviceNo(deviceNo);
	}
}
