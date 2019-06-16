package com.lphr.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lphr.annotations.ApiVersion;
import com.lphr.dto.sysUser.AddUserDTO;
import com.lphr.dto.sysUser.AdminPageInfoDTO;
import com.lphr.dto.sysUser.ListMyMenuDTO;
import com.lphr.dto.sysUser.LoginDTO;
import com.lphr.dto.sysUser.UpadateUserDTO;
import com.lphr.dto.sysUser.UpdateRoleDTO;
import com.lphr.entity.Menu;
import com.lphr.entity.MenuRole;
import com.lphr.service.MenuService;
import com.lphr.service.SysUserService;
import com.lphr.util.BeanMapper;
import com.lphr.util.MenuUtils;
import com.lphr.vo.CommonVO;
import com.lphr.vo.DataTableVO;
import com.lphr.vo.ListSysUserVO;
import com.lphr.vo.LoginVO;
import com.lphr.vo.MenuVO;

@RestController
@RequestMapping("{version}/sys/user")
public class UserController {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private MenuService menuService;

	/**
	 * @author YN
	 * @2019年4月22日@下午12:13:07
	 * @TODO:用户登录后检验用户登录，然后在redis保存用户token
	 */
	@ApiVersion(1)
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public CommonVO<LoginVO> login(@RequestBody @Valid LoginDTO dto, BindingResult result) {
		return sysUserService.login(dto);
	}

	/**
	 * @author: YN
	 * @Date: 2019年4月23日上午10:58:00
	 * @TODO: 根据用户id获取该用户所有菜单权限:
	 ****  需要先获取用户的角色集合，再通过角色获取菜单权限
	 * @param: 用户id
	 * @return: MenuVO
	 */
	@GetMapping(value = "listMyMenu")
	public CommonVO<List<MenuVO>> listMyMenu(@Valid ListMyMenuDTO dto, BindingResult bindResult) {
		int userId = dto.getUserId();
		
		// 1获取所有菜单
		List<Menu> sysMenuList = menuService.list();

		if (userId == -1) { // 如果是超级管理员，则返回所有菜单
			return getAllMenus(sysMenuList);
		}
		
		// 2获取用户下的所有 角色菜单集合
		List<MenuRole> sysMenuRoleList = menuService.findByUserId(userId);

		// 3整理出角色下的菜单Id集合
		List<Integer> menuRoleIds = new ArrayList<Integer>();
		if (!CollectionUtils.isEmpty(sysMenuRoleList)) {
			for (MenuRole sysMenuRole : sysMenuRoleList) {
				menuRoleIds.add(sysMenuRole.getMenuId());
			}
		}

		// 4 整理出用户拥有的菜单
		List<MenuVO> sysMenuVOList = new ArrayList<MenuVO>();
		if (!CollectionUtils.isEmpty(sysMenuList)) {
			for (Menu sysMenu : sysMenuList) {
				if (menuRoleIds.contains(sysMenu.getId())) {
					MenuVO sysMenuVO = new MenuVO();
					sysMenuVO.setId(sysMenu.getId());
					sysMenuVO.setParentId(sysMenu.getParentId());
					sysMenuVO.setMenuName(sysMenu.getMenuName());
					sysMenuVO.setMemo(sysMenu.getMemo());
					sysMenuVO.setMenuPath(sysMenu.getMenuPath());
					sysMenuVO.setMenuSort(sysMenu.getMenuSort());
					sysMenuVO.setIconCls(sysMenu.getIconCls());
					sysMenuVO.setScode(sysMenu.getScode());
					sysMenuVO.setItype(sysMenu.getItype());
					sysMenuVOList.add(sysMenuVO);
				}
			}
		}

		// 5 生成树形结构
		List<MenuVO> data = MenuUtils.formatMenu(sysMenuVOList);
		CommonVO<List<MenuVO>> commonVO = new CommonVO<>();
		commonVO.setData(data);
		return commonVO;
	}


	/**
	 * @author: YN
	 * @Date: 2019年4月23日上午11:09:58
	 * @TODO: 用户是超级管理员的情况，应该获取所有角色并获得所有菜单权限
	 * @param: 
	 * @return:
	 */
	private CommonVO<List<MenuVO>> getAllMenus(List<Menu> sysMenus) {// 管理员
		// 1 获取所有有效菜单
		List<Menu> sysMenuList = new ArrayList<>();
		for (Menu menu : sysMenus) {
			if (menu.getState() == 1 && menu.getFlagDel() == 0) {
				sysMenuList.add(menu);
			}
		}
		List<MenuVO> sysMenuVOList = BeanMapper.mapList(sysMenuList, MenuVO.class);

		// 2 生成树形结构
		List<MenuVO> data = MenuUtils.formatMenu(sysMenuVOList);
		CommonVO<List<MenuVO>> commonVO = new CommonVO<>();
		commonVO.setData(data);
		return commonVO;
	}

	/**
	 * @author: YN
	 * @Date: 2019年4月23日上午11:13:06
	 * @TODO: 获取所有后台用户，并分页展示
	 * @param: 
	 * @return: 分页展示所有用户
	 */
	@GetMapping("findSysUser")
	public CommonVO<DataTableVO<ListSysUserVO>> getSysUser(AdminPageInfoDTO dto) {
		return new CommonVO<DataTableVO<ListSysUserVO>>(sysUserService.getSysUser(dto));
	}

	/**
	 *  添加后台user
	 * @author YN
	 * @date   2019-04-21
	 * 
	 */
	@PostMapping("add")
	public CommonVO<Object> add(@RequestBody @Valid AddUserDTO dto, BindingResult result,
			@RequestHeader("x-user-id") Integer userId) {
		return sysUserService.addSysUser(dto, userId);
	}
 
	/**
	 *  修改后台user
	 * @author YN
	 * @date   2019-04-21
	 */
	@PostMapping("update")
	public CommonVO<Object> update(@RequestBody @Valid UpadateUserDTO dto, BindingResult result,
			@RequestHeader("x-user-id") Integer userId) {
		return sysUserService.updateSysUser(dto, userId);
	}
	
	/**
	 * @author: YN
	 * @Date: 2019年4月23日上午11:26:01
	 * @TODO: 更新后台用户角色，一个用户可拥有多个角色
	 * @param: 角色id为list参数
	 * @return: 
	 */
	@RequestMapping(value="updateUserRole",method=RequestMethod.POST)
	public CommonVO<Object> updateRole(@RequestBody @Valid UpdateRoleDTO dto,BindingResult result) {
		return new CommonVO<Object>(sysUserService.updateRole(dto));
	}
	
	/**
	 * @author: YN
	 * @Date: 2019年4月23日上午11:27:21
	 * @TODO: 删除后台用户，同时根据userid删除t_user_role表的记录
	 * @param: userID
	 * @return:
	 */
	@RequestMapping(value="deleteUserRole",method=RequestMethod.POST)
	public CommonVO<Object> deleteRole(@RequestBody @Valid UpdateRoleDTO dto,BindingResult result) {
		return new CommonVO<Object>(sysUserService.deleteRole(dto));
	}
}
