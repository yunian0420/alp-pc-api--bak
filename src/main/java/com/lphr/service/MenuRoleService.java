package com.lphr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lphr.entity.MenuRole;
import com.lphr.mapper.MenuRoleMapper;

@Service
public class MenuRoleService {

	@Autowired
	private MenuRoleMapper menuRoleMapper;

	public List<MenuRole> findMenuRoleByRoleId(Integer roleId) {
		//return menuRoleMapper.findMenuRoleByRoleId(roleId);
		MenuRole query = new MenuRole();
		query.setRoleId(roleId);
		query.setFlagDel(0);
		return menuRoleMapper.select(query);
	}

}
