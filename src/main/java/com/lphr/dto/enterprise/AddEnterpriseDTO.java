package com.lphr.dto.enterprise;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AddEnterpriseDTO {
	
	@NotEmpty( message = "enterpriseName必填")
    private String enterpriseName;
	
	@NotEmpty( message = "principal必填")
	private String principal;
	
	@NotEmpty( message = "contactWay必填")
    private String contactWay;
	
	@NotEmpty( message = "username必填")
	private String username;
	
	@NotEmpty( message = "password必填")
	private String password;
	
}
