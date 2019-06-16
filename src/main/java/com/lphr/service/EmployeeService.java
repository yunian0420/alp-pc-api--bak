package com.lphr.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lphr.dto.shop.AddShopEmployeeDTO;
import com.lphr.dto.shop.ListEmployeeDTO;
import com.lphr.dto.shop.UpdateShopEmployeeDTO;
import com.lphr.entity.ShopEmployee;
import com.lphr.entity.User;
import com.lphr.mapper.ShopEmployeeMapper;
import com.lphr.mapper.UserMapper;
import com.lphr.util.MD5;
import com.lphr.util.PhoneCheckUtils;
import com.lphr.util.RC4Util;
import com.lphr.vo.CommonVO;
import com.lphr.vo.DataTableVO;
import com.lphr.vo.ListEmployeeVO;


@Service
public class EmployeeService {

	@Autowired
	private ShopEmployeeMapper shopEmployeeMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * @author: YN
	 * @Date: 2019年4月29日下午5:24:11
	 *
	 */ 
	public DataTableVO<ListEmployeeVO> findByPage(ListEmployeeDTO dto) {
		PageHelper.startPage(dto.getPage(), dto.getSize());
		List<ListEmployeeVO> entitys = shopEmployeeMapper.findByPage(dto);
		PageInfo<ListEmployeeVO> page = new PageInfo<ListEmployeeVO>(entitys);
		int pageSize  = page.getPageSize();
		long allCount = page.getTotal();
		int allPage = page.getPages();
		int currentPage = page.getPageNum();
		DataTableVO<ListEmployeeVO> dataTable = new DataTableVO<ListEmployeeVO>(pageSize, allCount, allPage, currentPage, entitys);
		return dataTable;
	}

	/**
	 * @author: YN
	 * @Date: 2019年4月29日下午5:24:43
	 *
	 */ 
	public CommonVO<Object> addEmployee(AddShopEmployeeDTO dto, Integer userId) {
		CommonVO<Object> commonVO = new CommonVO<Object>();
		User u = new User();
		if(!PhoneCheckUtils.isPhone(dto.getUserPhone())) {
			commonVO.setCode(0);
			commonVO.setMessage("请输入正确的11位电话号码!");
			return commonVO;
		}
		u.setPhone(dto.getUserPhone());
		User test = userMapper.selectOne(u);
		if(null != test) {
			commonVO.setCode(0);
			commonVO.setMessage("电话号码重复，请重新选择!");
			return commonVO;
		}
		String inviteCode = RC4Util.encry_RC4_string(dto.getUserPhone().substring(4, 9),UUID.randomUUID().toString());
		u.setCreateUser(userId);
		u.setUsername(dto.getUserName());
		u.setNickName(dto.getUserName());
		u.setInviteCode(inviteCode);
		u.setPassword(MD5.getMD5(dto.getUserPhone() + dto.getPassword()));
		u.setUserType(2);
		userMapper.insertSelective(u);
		
		ShopEmployee shopEmployee = new ShopEmployee();
		shopEmployee.setEmployeeNo(dto.getEmployeeNo());
		shopEmployee.setShopId(dto.getShopId());
		shopEmployee.setUserId(u.getId());
		shopEmployee.setCreateUser(userId);
		shopEmployeeMapper.insertSelective(shopEmployee);
		return null;
	}

	/**
	 * @author: YN
	 * @Date: 2019年4月29日下午8:08:43
	 * @Todo: 是修改员工信息还是删除后再添加
	 */ 
	public CommonVO<Object> updateEmployee(UpdateShopEmployeeDTO dto, Integer userId) {
		return null;
	}

}
