package com.lphr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lphr.BaseMapper;
import com.lphr.entity.Menu;
import com.lphr.vo.MenuVO;


@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

	List<MenuVO> ListMenuByRoleId(Integer roleId);

}