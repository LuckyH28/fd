package com.aisino.domain.common.enums;

public enum QueueLevelEnum {
	
	Level_01("01","normal"),
	Level_02("02","exception");
	
	private String levelCode;
	private String levelName;
	
	QueueLevelEnum(String levelCode, String levelName){
		this.levelCode=levelCode;
		this.levelName=levelName;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

}
