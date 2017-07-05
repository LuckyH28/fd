package com.aisino.domain.busi.dao;

import com.aisino.domain.protocol.bean.busi.TopicInfoEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * DESCRIPTION：mqtt消息持久化接口
 *
 * @author LuckyH
 * @create 2017-03-16 16:15
 **/
public interface ITopicMsgDao {

    /**
     * 持久化消息
     */
    void saveTopicMsg(TopicInfoEntity topicInfoEntity)throws SQLException;


    /**
     * 删除消息
     */
    void deleteTopicMsg(TopicInfoEntity topicInfoEntity)throws SQLException;

    /**
     * 查询消息
     */
    List<TopicInfoEntity> queryTopicMsgs()throws SQLException;
    
    /**
     * 查询指定设备消息
     */
    List<TopicInfoEntity> queryTopicMsgsByDeviceNo(String deviceNo)throws SQLException;

}
