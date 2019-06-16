package com.lphr.enums;

public enum FlagDelEnum {

	NO(0,"未删除"),
	YES(1,"已删除");

	
	private int code;
	private String name;
	
	FlagDelEnum(int code,String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
