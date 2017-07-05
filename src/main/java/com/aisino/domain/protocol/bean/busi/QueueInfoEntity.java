package com.aisino.domain.protocol.bean.busi;

import java.util.Date;

/**
 * DESCRIPTION：队列相关实体对象
 *
 * @author LuckyH
 * @create 2017-03-17 10:36
 **/
public class QueueInfoEntity {

	private String id;
	/**
	 * 队列名称
	 */
	private String queueName;

	/**
	 * 队列消息生产者(例51SAAS)
	 */
	private String queueService;

	/**
	 * 队列消息消费者(例51FlyDove)
	 */
	private String queueConsumer;

	/**
	 * 队列消息业务类型代码
	 */
	private String queueBusiCode;

	/**
	 * 队列消息业务类型名称
	 */
	private String queueBusiType;

	/**
	 * 队列编号(e.g 平台商编码/数量)
	 */
	private String queueNO;

	/**
	 * 队列消息数量
	 */
	private String queueMessageCount;

	/**
	 * 队列消费者数量
	 */
	private String queueConsumerCount;

	/**
	 * 队列类型，0正常队列，1异常队列
	 */
	private String queueLevel;

	/**
	 * 队列操作类型 01新增队列 02启动队列监听
	 */
	private String czlx;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getQueueService() {
		return queueService;
	}

	public void setQueueService(String queueService) {
		this.queueService = queueService;
	}

	public String getQueueConsumer() {
		return queueConsumer;
	}

	public void setQueueConsumer(String queueConsumer) {
		this.queueConsumer = queueConsumer;
	}

	public String getQueueBusiType() {
		return queueBusiType;
	}

	public void setQueueBusiType(String queueBusiType) {
		this.queueBusiType = queueBusiType;
	}

	public String getQueueNO() {
		return queueNO;
	}

	public void setQueueNO(String queueNO) {
		this.queueNO = queueNO;
	}

	public String getQueueMessageCount() {
		return queueMessageCount;
	}

	public void setQueueMessageCount(String queueMessageCount) {
		this.queueMessageCount = queueMessageCount;
	}

	public String getQueueConsumerCount() {
		return queueConsumerCount;
	}

	public void setQueueConsumerCount(String queueConsumerCount) {
		this.queueConsumerCount = queueConsumerCount;
	}

	public String getQueueLevel() {
		return queueLevel;
	}

	public void setQueueLevel(String queueLevel) {
		this.queueLevel = queueLevel;
	}

	public String getCzlx() {
		return czlx;
	}

	public void setCzlx(String czlx) {
		this.czlx = czlx;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getQueueBusiCode() {
		return queueBusiCode;
	}

	public void setQueueBusiCode(String queueBusiCode) {
		this.queueBusiCode = queueBusiCode;
	}

}
