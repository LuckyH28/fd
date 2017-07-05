package com.aisino.domain.protocol.bean.busi;


/**
 * 王富生版本协议管理端接口
 * @author LuckyH
 *
 */
public class TmpBusiEntity {
	
	/**
	 * 0监听业务   1定时器业务
	 */
	private String busi;
	
	/**
	 * 0启动监听  1新增定时器  2删除定时器   3修改定时器
	 */
	private String order;
	
	/**
	 * 定时器业务bean 实现ID
	 */
	private String springId;
	
	/**
	 * 定时器业务类型
	 */
	private String jobBusiType;
	
	/**
	 * 时间表达式
	 */
	private String corn;

	public String getBusi() {
		return busi;
	}

	public void setBusi(String busi) {
		this.busi = busi;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSpringId() {
		return springId;
	}

	public void setSpringId(String springId) {
		this.springId = springId;
	}

	public String getJobBusiType() {
		return jobBusiType;
	}

	public void setJobBusiType(String jobBusiType) {
		this.jobBusiType = jobBusiType;
	}

	public String getCorn() {
		return corn;
	}

	public void setCorn(String corn) {
		this.corn = corn;
	}
	

}
