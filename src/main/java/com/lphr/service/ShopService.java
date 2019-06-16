package com.lphr.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lphr.dto.shop.AddShopDTO;
import com.lphr.dto.shop.ListShopDTO;
import com.lphr.entity.Shop;
import com.lphr.mapper.ShopMapper;
import com.lphr.util.BeanMapper;
import com.lphr.vo.DataTableVO;
import com.lphr.vo.ListShopVO;

@Service
public class ShopService {
	
	@Autowired
	private ShopMapper shopMapper;
	
	/**
	 * @author: YN
	 * @Date: 2019年4月29日上午11:04:34
	 */ 
	public DataTableVO<ListShopVO> findByPage(ListShopDTO dto) {
		PageHelper.startPage(dto.getPage(), dto.getSize());
		List<ListShopVO> entitys = shopMapper.findByPage(dto);
		PageInfo<ListShopVO> page = new PageInfo<ListShopVO>(entitys);
		int pageSize  = page.getPageSize();
		long allCount = page.getTotal();
		int allPage = page.getPages();
		int currentPage = page.getPageNum();
		DataTableVO<ListShopVO> dataTable = new DataTableVO<ListShopVO>(pageSize, allCount, allPage, currentPage, entitys);
		return dataTable;
	}

	/**
	 * @author: YN
	 * @Date: 2019年4月29日下午4:35:12
	 */ 
	public Object addShop(AddShopDTO dto, Integer userId) {
		Shop shop = BeanMapper.map(dto, Shop.class);
		shop.setCreateUser(userId);
		shopMapper.insertSelective(shop);
		return null;
	}

}
