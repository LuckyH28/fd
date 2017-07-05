package com.aisino.domain.busi.service;

import com.aisino.domain.protocol.bean.busi.TopicInfoEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * DESCRIPTION：主题消息管理服务
 *
 * @author LuckyH
 * @create 2017-03-18 10:28
 **/
public interface ITopicMsgService {

    /**
     * 持久化未发布消息
     * @param topicInfoEntity
     */
    void saveTopicMsg(TopicInfoEntity topicInfoEntity)throws Exception;

    /**
     * 未发布消息成功发布后进行去持久化
     * @param topicInfoEntity
     */
    void deleteTopicMsg(TopicInfoEntity topicInfoEntity)throws Exception;

    /**
     * 查询未发布成功的topic消息
     * @return
     */
    List<TopicInfoEntity> queryTopicMsgs()throws Exception;
    
    /**
     * 查询指定未发布成功的topic消息
     * @param deviceNo
     * @return
     * @throws Exception
     */
    List<TopicInfoEntity> queryTopicMsgsByDeviceNo(String deviceNo)throws Exception;
}
