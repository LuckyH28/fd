package com.aisino.domain.common.enums;

public enum ReturnCodeEnum {
	
	//通用错误代码
	Success("0000","成功!request success!"),
	Fail("9999","操作失败"),
	
	//外层接口校验相关错误代码
	RequestIsEmpty("9001","请求报文为空!RequestIsEmpty!"),
	IllegalBusiness("9002","非法业务请求!IllegalBusiness!"),
	JsonSyntaxException("","Json报文不规范!JsonSyntaxException!"),
	
	//内部通讯相关错误代码
	IllegalOrder("","非法业务指令!IllegalOrder!"),
	IllegalCzlx("","非法操作类型!IllegalCzlx!"),
	UnsupportedBusiness("","未开放业务类型!UnsupportedBusiness!"),
	
	;
	
	
	private String code;
	private String message;
	
	ReturnCodeEnum(String code, String message){
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
