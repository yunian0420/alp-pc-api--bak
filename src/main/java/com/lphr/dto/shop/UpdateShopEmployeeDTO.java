package com.lphr.dto.shop;

import javax.validation.constraints.NotNull;

import lombok.Data;
    
/**
 * @author: YN
 * @Date: 2019年4月29日下午8:07:27
 *
 */
@Data
public class UpdateShopEmployeeDTO {

	@NotNull(message="id必填")
	private Integer id;
	private String employeeNo;
	private String userName;
	private String userPhone;
	private String password;
	private Integer shopId;
	
}
