package com.lphr.dto.role;

import com.lphr.dto.PageInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ListRoleDTO extends PageInfo {

	public String roleName;
}
