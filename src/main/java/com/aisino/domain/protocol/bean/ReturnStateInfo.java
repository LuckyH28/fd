package com.aisino.domain.protocol.bean;

public class ReturnStateInfo {

	/**
	 * 返回代码
	 */
	private String returnCode;
	/**
	 * base64 返回描述
	 */
	private String returnMessage;
	
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMessage() {
		return returnMessage;
	}
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

}
