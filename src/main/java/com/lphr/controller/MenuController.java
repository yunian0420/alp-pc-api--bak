package com.lphr.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lphr.annotations.ApiVersion;
import com.lphr.dto.menu.AddMenuDTO;
import com.lphr.dto.menu.DetailOrDeleteMenuDTO;
import com.lphr.dto.menu.ListMenuByRoleIdDTO;
import com.lphr.dto.menu.UpdateMenuDTO;
import com.lphr.entity.Menu;
import com.lphr.entity.MenuRole;
import com.lphr.service.MenuRoleService;
import com.lphr.service.MenuService;
import com.lphr.util.BeanMapper;
import com.lphr.util.MenuUtils;
import com.lphr.vo.CommonVO;
import com.lphr.vo.MenuVO;

@RestController
@RequestMapping("{version}/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@Autowired
	private MenuRoleService menuRoleService;

	/**
	 * 
	 * @author YN
	 * @2019年4月22日下午4:05:53
	 * @TODO:查询所有菜单数据
	 * @param:
	 * @return:以树形结构展示所有菜单数据
	 *         返回值中有一个checked字段，用于角色or用户模块时的选中状态
	 */
	@ApiVersion(1)
	@GetMapping("listAll")
	public CommonVO<List<MenuVO>> findAll() {
		
		List<Menu> listAll = menuService.list();
		List<Menu> list = new ArrayList<Menu>();
		for (Menu menu : listAll) {
			if (menu.getState() == 1 && menu.getFlagDel() == 0) {
				list.add(menu);
			}
		}
		List<MenuVO> listMenuVO = BeanMapper.mapList(list, MenuVO.class);
		List<MenuVO> data = MenuUtils.formatMenu(listMenuVO);
		return new CommonVO<List<MenuVO>>(data);
	}

	/**
	 * 
	 * @author YN
	 * @Date: 2019年4月22日下午4:28:30
	 * @TODO: 添加菜单
	 * @param:
	 * @return:
	 */
	@ApiVersion(1)
	@PostMapping("add")
	public CommonVO<Object> addMenu(@RequestBody @Valid AddMenuDTO dto, BindingResult result,
			@RequestHeader("X-User-Id") Integer userId) {
		return new CommonVO<Object>(menuService.addMenu(dto,userId));
	}

	@ApiVersion(1)
	@PostMapping("update")
	public CommonVO<Object> updateMenu(@Valid @RequestBody UpdateMenuDTO dto, BindingResult result,
			@RequestHeader("X-User-Id") Integer userId) {
		return new CommonVO<Object>(menuService.updateMenu(dto,userId));
	}

	/**
	 * 
	 * @author: YN
	 * @Date: 2019年4月22日下午5:06:38
	 * @TODO: 通过主键id查询菜单详情
	 * @param: id
	 * @return: 菜单详情
	 */
	@ApiVersion(1)
	@GetMapping("detail")
	public CommonVO<MenuVO> detailMenu(@Valid DetailOrDeleteMenuDTO dto, BindingResult result) {
		return new CommonVO<MenuVO>(menuService.detailMenu(dto.getId()));
	}

	/**
	 * @author: YN
	 * @Date: 2019年4月22日下午5:24:45
	 * @TODO: 通过主键id逻辑删除删除数据
	 * @param: 主键id
	 * @return:
	 */
	@ApiVersion(1)
	@PostMapping("delete")
	public CommonVO<Object> deleteMenu(@Valid @RequestBody DetailOrDeleteMenuDTO dto, BindingResult result,
			@RequestHeader("X-User-Id") Integer userId) {
		return new CommonVO<>(menuService.deleteMenu(dto.getId(),userId));
	}

	/**
	 * @author: YN
	 * @Date: 2019年4月22日下午5:25:53
	 * @TODO: 但用户登录进后台时，需要给用户展示的菜单列表
	 * @param: 
	 * @return: 返回的菜单数据需要以树形结构展示
	 */
	@ApiVersion(1)
	@GetMapping("/listMenuByRoleId")
	public CommonVO<List<MenuVO>> ListMenuByRoleId(ListMenuByRoleIdDTO dto) {
		List<Menu> listAll = menuService.list();
		//1所有的有效菜单
		List<Menu> listMenu = new ArrayList<Menu>();
		for (Menu menu : listAll) {
			if (menu.getState() == 1 && menu.getFlagDel() == 0) {
				listMenu.add(menu);
			}
		}
		if(dto.getRoleId() == -1) {
			return getAllMenus(listAll);
		}
		//2该角色下的所有 菜单角色 集合
		List<MenuRole> listMenuRole = menuRoleService.findMenuRoleByRoleId(dto.getRoleId());

		// 3整理出角色下的菜单Id集合
		List<Integer> menuRoleIds = new ArrayList<Integer>();
		if (!CollectionUtils.isEmpty(listMenuRole)) {
			for (MenuRole sysMenuRole : listMenuRole) {
				menuRoleIds.add(sysMenuRole.getMenuId());
			}
		}

		// 4 整理出角色拥有的菜单集合
		List<MenuVO> sysMenuVOList = new ArrayList<MenuVO>();
		if(!CollectionUtils.isEmpty(listMenu)) {
			for(Menu sysMenu : listMenu) {
				MenuVO sysMenuVO = new MenuVO();
				sysMenuVO.setId(sysMenu.getId());
				sysMenuVO.setParentId(sysMenu.getParentId());
				sysMenuVO.setMenuName(sysMenu.getMenuName());
				sysMenuVO.setMenuPath(sysMenu.getMenuPath());
				sysMenuVO.setMenuSort(sysMenu.getMenuSort());
				//菜单设置成选中状态
				if(menuRoleIds.contains(sysMenuVO.getId())) {
					sysMenuVO.setChecked("true");//TODO
				} 
				sysMenuVOList.add(sysMenuVO);
			}
		}
		
		//生成树形结构数据
		List<MenuVO> data = MenuUtils.formatMenu(sysMenuVOList);
		return new CommonVO<List<MenuVO>>(data);
	}

	/**
	 * @author: YN
	 * @Date: 2019年4月23日上午10:41:11
	 * @TODO: 如果是超级管理员，则拥有所有菜单权限
	 * @param: 
	 * @return: 
	 */ 
	private CommonVO<List<MenuVO>> getAllMenus(List<Menu> listAll) {
		List<MenuVO> menuVOs = BeanMapper.mapList(listAll, MenuVO.class);
		for(MenuVO menuVO : menuVOs) {
			menuVO.setChecked("true");
		}
		
		List<MenuVO> data = MenuUtils.formatMenu(menuVOs);
		return new CommonVO<List<MenuVO>>(data);
	}


}
