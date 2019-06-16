package com.lphr.dto.shop;

import lombok.Data;

/**
 * @author: YN
 * @Date: 2019年4月29日下午5:20:55
 *
 */
@Data
public class AddShopEmployeeDTO {
	
	private String employeeNo;
	private String userName;
	private String userPhone;
	private String password;
	private Integer shopId;
	
}
