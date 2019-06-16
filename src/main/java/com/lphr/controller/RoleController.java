package com.lphr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lphr.dto.role.AddMenuRoleDTO;
import com.lphr.dto.role.ListRoleDTO;
import com.lphr.dto.role.RoleIdDTO;
import com.lphr.dto.role.UpdateMenuRoleDTO;
import com.lphr.service.RoleService;
import com.lphr.vo.CommonVO;
import com.lphr.vo.DataTableVO;
import com.lphr.vo.RoleVO;

@RestController
@RequestMapping("{version}/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	/**
	 * 
	 * @author YN
	 * @2019年4月22日下午3:42:47
	 * @TODO：查找所有的角色，分页展示，可模糊查询条件为roleName;
	 * @return: 所有角色列表
	 */
	@GetMapping("/findAll")
	public CommonVO<DataTableVO<RoleVO>> findAll(ListRoleDTO dto) {
		return new CommonVO<>(roleService.findAll(dto));
	}
	
	/**
	 * 
	 * @author YN
	 * @Data: 2019年4月22日下午3:53:20
	 * @TODO: 添加一个角色，同时可添加这个角色的菜单权限
	 * @param: 需注意的一个参数menuIds，表示这个角色的菜单权限的id集合
	 * @return:
	 */
	@PostMapping("/addRoleMenu")
	public CommonVO<Object> addOrUpdateRole(@RequestBody AddMenuRoleDTO dto,BindingResult result,
			@RequestHeader("X-User-Id") Integer userId) {
		return new CommonVO<>(roleService.addRoleMenu(dto,userId));
	}
	
	/**
	 * @author: YN
	 * @Date: 2019年4月23日上午10:33:26
	 * @TODO: 修改一个角色，及该角色的菜单权限
	 * @param: 需注意的一个参数menuIds，表示这个角色的菜单权限的id集合
	 * @return: 
	 */
	@PostMapping("/updateRoleMenu")
	public CommonVO<Object> updateRoleMenu(@RequestBody UpdateMenuRoleDTO dto,BindingResult result,
			@RequestHeader("X-User-Id") Integer userId) {
		return new CommonVO<>(roleService.updateRoleMenu(dto,userId));
	}
	
	/**
	 * @author: YN
	 * @Date: 2019年4月23日上午10:35:14
	 * @TODO: 逻辑删除该角色
	 * @param: 
	 * @return:
	 */
	@PostMapping("/deleteRoleMenu")
	public CommonVO<Object> deleteRoleMenu(@RequestBody @Valid RoleIdDTO dto,BindingResult result,
			@RequestHeader("X-User-Id") Integer userId) {
		return new CommonVO<>(roleService.deleteRoleMenu(dto.getRoleId(),userId));
	}
}
