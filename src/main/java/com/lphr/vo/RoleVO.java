package com.lphr.vo;

import java.util.Date;

import lombok.Data;

@Data
public class RoleVO {

	private Integer id;
	private String roleName;
	private String roleDesc;
	private Date createTime;
}
