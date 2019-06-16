package com.lphr.dto.sysUser;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AddUserDTO {
	
	@NotEmpty(message = "账号必填")
	private String username;
	
	@NotEmpty(message = "密码必填")
	private String password;
	
	private String nickName;
}
