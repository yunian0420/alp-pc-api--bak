package com.lphr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lphr.BaseMapper;
import com.lphr.dto.shop.ListEmployeeDTO;
import com.lphr.entity.ShopEmployee;
import com.lphr.vo.ListEmployeeVO;

@Mapper
public interface ShopEmployeeMapper extends BaseMapper<ShopEmployee>{

	/**
	 * @author: YN
	 * @Date: 2019年4月29日下午5:27:20
	 *
	 */ 
	List<ListEmployeeVO> findByPage(ListEmployeeDTO dto);

}