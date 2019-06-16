package com.lphr.vo;

import lombok.Data;

/**
 * @author: YN
 * @Date: 2019年4月29日下午5:14:39
 *
 */
@Data
public class ListEmployeeVO {
	
	private Integer id;
	private String employeeNo;
	private Integer userId;
	private String userName;
	private String userPhone;
	private Integer shopId;
	private String shopName;
	private String lastLoginTime;
	private String lastLoginIp;
    
    

}
