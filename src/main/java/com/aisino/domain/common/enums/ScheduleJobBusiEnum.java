package com.aisino.domain.common.enums;

public enum ScheduleJobBusiEnum  {
	
	ScheduleJob_Republic("01","republishTask","republishTask","execute");
	
	private String jobBusiCode;
	private String jobBusiType;
	private String springBeanID;
	private String method;
	
	
	ScheduleJobBusiEnum(String jobBusiCode, String jobBusiType, String springBeanID, String method){
		
		this.jobBusiCode=jobBusiCode;
		
		this.jobBusiType=jobBusiType;
		
		this.springBeanID=springBeanID;
		
		this.method=method;
		
	}
	
	public String getJobBusiCode() {
		return jobBusiCode;
	}


	public void setJobBusiCode(String jobBusiCode) {
		this.jobBusiCode = jobBusiCode;
	}

	public String getJobBusiType() {
		return jobBusiType;
	}


	public void setJobBusiType(String jobBusiType) {
		this.jobBusiType = jobBusiType;
	}


	public String getSpringBeanID() {
		return springBeanID;
	}


	public void setSpringBeanID(String springBeanID) {
		this.springBeanID = springBeanID;
	}


	public String getMethod() {
		return method;
	}


	public void setMethod(String method) {
		this.method = method;
	}
	
	@Override
	public String toString() {
		return "jobBusiCode:" + this.jobBusiCode + ";jobBusiType:" + this.jobBusiType + ";springBeanID:" + this.springBeanID + ";method:" + this.method +";";
	}




}
