package com.lphr.dto.role;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * @author: YN
 * @Date: 2019年4月22日下午5:38:20
 * @TODO: 
 * @param: 
 * @return: 
 */
@Data
public class AddMenuRoleDTO {

	@NotEmpty(message="roleName不能为空")
	private String roleName;
	private String roleDesc;
	private String roleCode; //这个参数可取消
	private List<String> menuIds;
}
