package com.lphr.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.lphr.constants.Token;
import com.lphr.dto.sysUser.AddUserDTO;
import com.lphr.dto.sysUser.AdminPageInfoDTO;
import com.lphr.dto.sysUser.LoginDTO;
import com.lphr.dto.sysUser.UpadateUserDTO;
import com.lphr.dto.sysUser.UpdateRoleDTO;
import com.lphr.entity.User;
import com.lphr.entity.UserRole;
import com.lphr.enums.StateEnum;
import com.lphr.enums.UserTypeEnum;
import com.lphr.manager.TokenManager;
import com.lphr.mapper.UserMapper;
import com.lphr.mapper.UserRoleMapper;
import com.lphr.util.BeanMapper;
import com.lphr.util.MD5;
import com.lphr.vo.CommonVO;
import com.lphr.vo.DataTableVO;
import com.lphr.vo.ListSysUserVO;
import com.lphr.vo.LoginVO;

@Service
public class SysUserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private TokenManager tokenManager;

	public CommonVO<LoginVO> login(LoginDTO dto) {
		CommonVO<LoginVO> commonVO = new CommonVO<>();
		User user = userMapper.findByUsername(dto.getUsername());
		if(user == null || !dto.getPassword().equals(user.getPassword())) {
			commonVO.setCode(0);
			commonVO.setMessage("登录名或密码错误!");
			return commonVO;
		}
		Token token = tokenManager.createToken(String.valueOf(user.getId()));
		LoginVO vo = new LoginVO();
		vo.setNickName(user.getNickName());
		vo.setUserId(user.getId());
		vo.setPhone(dto.getUsername());
		vo.setTokenId(token.getTokenId());
		commonVO.setData(vo);
		return commonVO;
	}

	
	public DataTableVO<ListSysUserVO> getSysUser(AdminPageInfoDTO dto) {
		User u = new User();
		u.setState(StateEnum.VALID.getCode());
		u.setFlagDel(0);
		u.setUserType(UserTypeEnum.PLATFORM.getCode());
		PageHelper.startPage(dto.getPage(),dto.getSize());
		List<User> entitys = userMapper.select(u);
		PageInfo<User> page = new PageInfo<User>(entitys);
		int pageSize = page.getPageSize();
		long allCount = page.getTotal();
		int allPage = page.getPages();
		int currentPage = page.getPageNum();
		
		List<ListSysUserVO> userVOs = BeanMapper.mapList(entitys, ListSysUserVO.class);
		/**
		 * 根据用户id查出用户的所属角色
		 */
		for(ListSysUserVO userVO : userVOs) {
			UserRole record = new UserRole();
			record.setState(StateEnum.VALID.getCode());
			record.setFlagDel(0);
			record.setUserId(userVO.getId());
			List<UserRole> userRoles =  userRoleMapper.select(record);
			userVO.setRoleIds(getRoleIds(userRoles));
		}
		
		DataTableVO<ListSysUserVO> dataTableVO = new DataTableVO<ListSysUserVO>(pageSize, allCount, allPage, currentPage, userVOs);
		return dataTableVO;
	}
	
	private List<Integer> getRoleIds(List<UserRole> userRoles) {
		List<Integer> roleIds = new ArrayList<Integer>();
		for(UserRole item : userRoles) {
			roleIds.add(item.getRoleId());
		}
		return roleIds;
	}


	public CommonVO<Object> addSysUser(AddUserDTO dto, Integer userId) {
		CommonVO<Object> commonVO = new CommonVO<Object>();
		User u = new User();
		u.setUsername(dto.getUsername());
		User record = userMapper.selectOne(u);
		if(record != null) {
			commonVO.setCode(0);
			commonVO.setMessage("用户名已存在，请重新输入！");
			return commonVO;
		}
		u.setCreateUser(userId);
		u.setUserType(UserTypeEnum.PLATFORM.getCode());
		u.setNickName(dto.getNickName());
		if(dto.getNickName() == "" || dto.getNickName() == null) {
			u.setNickName(dto.getUsername());
		}
		u.setPassword(MD5.getMD5(dto.getPassword()));
//		String inviteCode = RC4Util.encry_RC4_string(dto.getUserPhone().substring(4, 9),UUID.randomUUID().toString());
//		u.setInviteCode(inviteCode);
		userMapper.insertSelective(u);
		return commonVO;
	}

	public CommonVO<Object> updateSysUser(UpadateUserDTO dto, Integer userId) {
		CommonVO<Object> commonVO = new CommonVO<Object>(); 
		User u = new User();
		u.setId(dto.getId());
		u.setUsername(dto.getUsername());
		//不能改username的逻辑
		User record = userMapper.selectOne(u);
		if(null == record) {
			commonVO.setCode(0);
			commonVO.setMessage("不能修改用户名！");
			return commonVO;
		}
		
		u.setNickName(dto.getNickName());
		u.setUpdateUser(userId);
		u.setUpdateTime(new Date());
		if(StringUtil.isNotEmpty(dto.getPassword())) {
			u.setPassword(MD5.getMD5(dto.getPassword()));
		}
		userMapper.updateByPrimaryKeySelective(u);
		return commonVO;
	}


	public Object updateRole(UpdateRoleDTO dto) {
		int userId = dto.getUserId();
		UserRole query = new UserRole();
		query.setUserId(userId);
		userRoleMapper.delete(query);
		//String[] roleIds = dto.getRoleIds().split(",");
		if(dto.getRoleIds().size() > 0) {
			for(String roleId : dto.getRoleIds()) {
				UserRole record = new UserRole();
				record.setUserId(userId);
				record.setRoleId(Integer.valueOf(roleId));
				userRoleMapper.insertSelective(record);
			}
		}
		return null;
	}


	public Object deleteRole(UpdateRoleDTO dto) {
		User record = new User();
		record.setId(dto.getUserId());
		record.setUserType(UserTypeEnum.CUSTOMER.getCode());
		record.setFlagDel(1);
		userMapper.updateByPrimaryKeySelective(record);
		
		//直接物理删除
		UserRole userRole = new UserRole();
		userRole.setUserId(dto.getUserId());
		userRoleMapper.delete(userRole);
		return null;
	}





	

}
