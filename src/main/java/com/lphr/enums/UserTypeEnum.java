package com.lphr.enums;

public enum UserTypeEnum {

	
	CUSTOMER(1,"用户"),
	PLATFORM(2,"后台用户"),
	ADMIN(3,"后台管理员");
	
	private int code;
	private String name;
	
	private UserTypeEnum(int code, String name) {
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
