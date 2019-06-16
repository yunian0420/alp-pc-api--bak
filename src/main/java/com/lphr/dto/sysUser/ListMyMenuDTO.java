package com.lphr.dto.sysUser;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ListMyMenuDTO {

	@NotNull(message = "用户ID必填")
	private Integer userId;
}
