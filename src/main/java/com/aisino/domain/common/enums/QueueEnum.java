package com.aisino.domain.common.enums;


public enum QueueEnum {
	
	Queue_PublicMessage("01","publishMessage");
	
	private String queueBusiCode;
	
	private String queueBusiType;
	
	
	QueueEnum(String queueBusiCode, String queueBusiType){
		this.setQueueBusiCode(queueBusiCode);
		this.setQueueBusiType(queueBusiType);
	}


	public String getQueueBusiCode() {
		return queueBusiCode;
	}


	public void setQueueBusiCode(String queueBusiCode) {
		this.queueBusiCode = queueBusiCode;
	}


	public String getQueueBusiType() {
		return queueBusiType;
	}


	public void setQueueBusiType(String queueBusiType) {
		this.queueBusiType = queueBusiType;
	}
}
