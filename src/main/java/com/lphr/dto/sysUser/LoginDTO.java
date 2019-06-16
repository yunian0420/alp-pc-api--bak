package com.lphr.dto.sysUser;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginDTO {

	@NotEmpty(message = "username必填")
	private String username;
	@NotEmpty(message = "password必填")
	private String password;
	
}
