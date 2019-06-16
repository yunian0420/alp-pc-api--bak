package com.lphr.dto.sysUser;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateRoleDTO {

	@NotNull(message = "用户ID必填")
	private Integer userId;
	private List<String> roleIds;
}
