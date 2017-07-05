package com.aisino.domain.common.enums;

public class CzlxConstant {
	
	/**
	 * 创建队列
	 */
	public static final String QUEUE_CREATE = "01";
	/**
	 * 创建监听
	 */
	public static final String QUEUELISTENER_CREATE = "02";
	
	
	/**
	 * 创建定时器
	 */
	public static final String SCHEDULEJOB_CREATE = "01";
	/**
	 * 查询定时器
	 */
	public static final String SCHEDULEJOB_RETRIEVE = "02";
	/**
	 * 更新定时器
	 */
	public static final String SCHEDULEJOB_UPDATE = "03";
	/**
	 * 删除定时器
	 */
	public static final String SCHEDULEJOB_DELETE = "04";
	
	/**
	 * 内部系统使用
	 * 创建RabbitMq队列监听
	 */
	public static final String ORDER_CREATE_MQLISTENER="0";
	
	/**
	 * 内部系统使用
	 * 创建定时器
	 */
	public static final String ORDER_CREATE_SCHEDULEJOB="1";
	
	/**
	 * 内部系统使用
	 * 删除定时器
	 */
	public static final String ORDER_DELETE_SCHEDULEJOB="2";
	
	/**
	 * 内部系统使用
	 * 修改定时器
	 */
	public static final String ORDER_UPDATE_SCHEDULEJOB="3";
	

}
