package com.lphr.dto.sysUser;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpadateUserDTO {
	
	@NotNull(message = "id必填")
	private Integer id;
	
	@NotEmpty(message = "用户名必填")
	private String username;
	
	private String password;
	private String nickName;
}
