package com.lphr.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lphr.dto.enterprise.AddEnterpriseDTO;
import com.lphr.dto.enterprise.ListEnterpriseDTO;
import com.lphr.entity.Enterprise;
import com.lphr.entity.User;
import com.lphr.enums.UserTypeEnum;
import com.lphr.mapper.EnterpriseMapper;
import com.lphr.mapper.UserMapper;
import com.lphr.util.BeanMapper;
import com.lphr.util.MD5;
import com.lphr.vo.BaseVO;
import com.lphr.vo.DataTableVO;
import com.lphr.vo.ListEnterpriseVO;

@Service
public class EnterpriseService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private EnterpriseMapper enterpriseMapper;
	
	/**
	 * 主体列表
	 * 
	 * @author 喻聪
	 * @date   2019-05-01
	 */ 
	public DataTableVO<ListEnterpriseVO> findByPage(ListEnterpriseDTO dto) {
		PageHelper.startPage(dto.getPage(), dto.getSize());
		List<ListEnterpriseVO> entitys = enterpriseMapper.findByPage(dto);
		PageInfo<ListEnterpriseVO> page = new PageInfo<ListEnterpriseVO>(entitys);
		int pageSize  = page.getPageSize();
		long allCount = page.getTotal();
		int allPage = page.getPages();
		int currentPage = page.getPageNum();
		return  new DataTableVO<>(pageSize, allCount, allPage, currentPage, entitys);
	}

	/**
	 * 新增主体
	 * 
	 * @author 喻聪
	 * @date   2019-05-01
	 */ 
	public BaseVO add(AddEnterpriseDTO dto, Integer userId) {
		
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setPassword(MD5.getMD5(dto.getPassword()));
		user.setUserType(UserTypeEnum.ADMIN.getCode());
		user.setCreateUser(userId);
		user.setNickName(dto.getUsername());
		userMapper.insertSelective(user);
		
		Enterprise shop = BeanMapper.map(dto, Enterprise.class);
		shop.setUserId(user.getId());
		shop.setCreateUser(userId);
		enterpriseMapper.insertSelective(shop);
		
		return new BaseVO();
	}

	

}
