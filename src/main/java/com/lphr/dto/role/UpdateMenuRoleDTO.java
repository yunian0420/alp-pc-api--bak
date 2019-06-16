package com.lphr.dto.role;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author: YN
 * @Date: 2019年4月22日下午5:43:54
 * @TODO: 
 * @param: 
 * @return: 
 */
@Data
public class UpdateMenuRoleDTO {
	
	@NotNull(message="roleId必填")
	private Integer roleId;
	
	@NotEmpty(message="roleName不能为空")
	private String roleName;
	private String roleCode;
	private String roleDesc;
	private List<String> menuIds;
}
