package com.lphr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lphr.BaseMapper;
import com.lphr.entity.MenuRole;

@Mapper
public interface MenuRoleMapper extends BaseMapper<MenuRole>{
    
	List<MenuRole> findByRoleIds(@Param("roleIds") List<Integer> roleIds);

	List<MenuRole> findMenuRoleByRoleId(@Param("roleId") Integer roleId);

	void deleteAllByRoleId(@Param("roleId") Integer roleId);
}