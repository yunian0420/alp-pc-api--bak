package com.lphr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lphr.BaseMapper;
import com.lphr.dto.role.ListRoleDTO;
import com.lphr.entity.Role;
import com.lphr.vo.RoleVO;


@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    

	List<RoleVO> findAll(ListRoleDTO dto);
}