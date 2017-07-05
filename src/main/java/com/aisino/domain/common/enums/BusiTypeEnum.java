package com.aisino.domain.common.enums;

public enum BusiTypeEnum {
	
	ScheduleJobManager("busi_manager_schedulejob","定时器管理"),
	QueueManager("busi_manager_queue","队列管理"),
	PublishMessage("busi_publish_message","消息发布"),
	SubscribeTopic("busi_subscribe_topic","订阅主题"),
	UnsubscribeTopic("busi_unsubscribe_topic","取消主题订阅"),
	DeviceInfo("busi_manager_deviceinfo","设备信息管理");
	
	private String key;
	private String value;
	
	BusiTypeEnum(String key, String value){
		this.key = key ;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.key+":"+this.value;
	}

}
