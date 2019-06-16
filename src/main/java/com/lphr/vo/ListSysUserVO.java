package com.lphr.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ListSysUserVO {
	private Integer id;
	private String phone;
	private String username;
	private String nickName;
	private Date createTime;
	private Integer state;
	
	private List<Integer> roleIds;
}
