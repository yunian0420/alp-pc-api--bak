package com.lphr.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lphr.dto.role.AddMenuRoleDTO;
import com.lphr.dto.role.ListRoleDTO;
import com.lphr.dto.role.UpdateMenuRoleDTO;
import com.lphr.entity.MenuRole;
import com.lphr.entity.Role;
import com.lphr.mapper.MenuRoleMapper;
import com.lphr.mapper.RoleMapper;
import com.lphr.vo.DataTableVO;
import com.lphr.vo.RoleVO;

@Service
public class RoleService extends BaseService<Role,RoleMapper>{

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private MenuRoleMapper menuRoleMapper;
	
	@Override
	public RoleMapper getMapper() {
		return roleMapper;
	}
	
	public DataTableVO<RoleVO> findAll(ListRoleDTO dto) {
		
		PageHelper.startPage(dto.getPage(), dto.getSize());
		List<RoleVO> entitys = roleMapper.findAll(dto);
		PageInfo<RoleVO> page = new PageInfo<RoleVO>(entitys);
		int pageSize = page.getPageSize();
		long allCount = page.getTotal();
		int allPage = page.getPages();
		int currentPage = page.getPageNum();
		DataTableVO<RoleVO> dataTableVO = new DataTableVO<>(pageSize, allCount, allPage, currentPage, entitys);
		return dataTableVO;
	}

	public Object addRoleMenu(AddMenuRoleDTO dto,Integer userId) {
		Role record = new Role();
		record.setRoleCode(dto.getRoleCode());
		if(dto.getRoleCode() == null) {
			record.setRoleCode("string");
		} 
		record.setRoleDesc(dto.getRoleDesc());
		record.setRoleName(dto.getRoleName());
		record.setCreateTime(new Date());
		super.add(record,userId);
		if(dto.getMenuIds().size() > 0) {
			for(String menuId : dto.getMenuIds()) {
				MenuRole menuRole = new MenuRole();
				menuRole.setMenuId(Integer.parseInt(menuId));
				menuRole.setRoleId(record.getId());
				menuRoleMapper.insertSelective(menuRole);
			}
			
		}
		return null;
	}

	public Object updateRoleMenu(UpdateMenuRoleDTO dto,int userId) {
		Role record = new Role();
		record.setId(dto.getRoleId());
		record.setRoleDesc(dto.getRoleDesc());
		record.setRoleName(dto.getRoleName());
		super.update(record,userId);
		
		menuRoleMapper.deleteAllByRoleId(dto.getRoleId());
		
		if(dto.getMenuIds().size() > 0) {
			for(String menuId : dto.getMenuIds()) {
				MenuRole menuRole = new MenuRole();
				menuRole.setMenuId(Integer.parseInt(menuId));
				menuRole.setRoleId(record.getId());
				menuRoleMapper.insertSelective(menuRole);
			}
		}
		
		return null;
	}

	public Object deleteRoleMenu(int roleId,int userId) {
		Role record = new Role();
		record.setId(roleId);
		record.setFlagDel(1);
		super.update(record,userId);
		
		return null;
	}

	


}
