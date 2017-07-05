package com.aisino.domain.protocol.bean.busi;

/**
 * Created by LuckyH on 2017-03-15.
 *
 * 主题消息实体对象
 *
 */
public final class TopicInfoEntity {

    /**
     * 消息ID
     */
    private String id;
    /**
     * 消息发布方
     */
    private String publisher;
    /**
     * 消息订阅方
     */
    private String subscriber;
    /**
     * 消息订阅者，设备编号
     */
    private String deviceNo;
    /**
     * 消息主题
     */
    private String topic;
    /**
     * 消息内容
     */
    private String message;
    
    /**
     * 插入时间
     */
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
