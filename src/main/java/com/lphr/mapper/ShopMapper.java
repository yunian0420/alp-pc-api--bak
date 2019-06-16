package com.lphr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lphr.BaseMapper;
import com.lphr.dto.shop.ListShopDTO;
import com.lphr.entity.Shop;
import com.lphr.vo.ListShopVO;

@Mapper
public interface ShopMapper extends BaseMapper<Shop>{

	/**
	 * @author: YN
	 * @Date: 2019年4月29日上午11:07:28
	 */ 
	List<ListShopVO> findByPage(ListShopDTO dto);
    
}