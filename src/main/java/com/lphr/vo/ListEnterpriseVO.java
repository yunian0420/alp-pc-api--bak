package com.lphr.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ListEnterpriseVO {
	
	private Integer id;
	private Integer userId;
    private String enterpriseName;
    private String principal;
    private String contactWay;
	private Date createTime;
    private Integer state;
    private String username;
	
}
