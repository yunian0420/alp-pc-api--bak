package com.lphr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lphr.dto.menu.AddMenuDTO;
import com.lphr.dto.menu.UpdateMenuDTO;
import com.lphr.entity.Menu;
import com.lphr.entity.MenuRole;
import com.lphr.entity.UserRole;
import com.lphr.enums.StateEnum;
import com.lphr.mapper.MenuMapper;
import com.lphr.mapper.MenuRoleMapper;
import com.lphr.mapper.UserRoleMapper;
import com.lphr.util.BeanMapper;
import com.lphr.vo.MenuVO;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;


@Service
public class MenuService extends BaseService<Menu, MenuMapper> {

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private MenuRoleMapper menuRoleMapper;
	
	@Override
	public MenuMapper getMapper() {
		return menuMapper;
	}
	
	public List<MenuRole> findByUserId(int userId) {
		UserRole query = new UserRole();
		query.setUserId(userId);
		query.setState(StateEnum.VALID.getCode());
		List<UserRole> userRoles = userRoleMapper.select(query);
		List<Integer> roleIds = new ArrayList<Integer>();
		roleIds.add(0);//防止roleIds为空，查询出异常
		for(UserRole userRole : userRoles) {
			roleIds.add(userRole.getRoleId());
		}
		return menuRoleMapper.findByRoleIds(roleIds);
	}

	public List<Menu> list() {
		return super.list();
	}

	public Object addMenu(AddMenuDTO dto,Integer userId) {
		Menu menu = BeanMapper.map(dto, Menu.class);
		return super.add(menu,userId);
	}

	public Object updateMenu(UpdateMenuDTO dto,int userId) {
		Menu menu = BeanMapper.map(dto, Menu.class);
		return super.update(menu,userId);
	}

	public MenuVO detailMenu(int id) {
		Menu menu =  super.detail(id);
		MenuVO menuVO = BeanMapper.map(menu, MenuVO.class);
		return menuVO;
	}

	public Object deleteMenu(int id,int userId) {
		Menu menu =  super.detail(id);
		menu.setState(StateEnum.INVALID.getCode());
		menu.setFlagDel(1);
		Menu menuSon = new Menu();
		menuSon.setState(StateEnum.INVALID.getCode());
		menuSon.setFlagDel(1);
		Example example = new Example(Menu.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("parentId", id);
		menuMapper.updateByExampleSelective(menuSon, example);
		return super.update(menu,userId);
	}
	

}
