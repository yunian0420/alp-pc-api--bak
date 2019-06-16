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

import com.lphr.annotations.ApiVersion;
import com.lphr.dto.shop.AddShopDTO;
import com.lphr.dto.shop.ListShopDTO;
import com.lphr.service.ShopService;
import com.lphr.vo.CommonVO;
import com.lphr.vo.DataTableVO;
import com.lphr.vo.ListShopVO;

/**
 * @author: YN
 * @Date: 2019年4月29日上午10:51:27
 *
 */
@RestController
@RequestMapping("{version}/shop")
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
	@ApiVersion(1)
	@GetMapping("list")
	public CommonVO<DataTableVO<ListShopVO>> list(ListShopDTO dto) {
		return new CommonVO<DataTableVO<ListShopVO>>(shopService.findByPage(dto));
	}
	
	@ApiVersion(1)
	@PostMapping("add")
	public CommonVO<Object> addMember(@RequestBody @Valid AddShopDTO dto, BindingResult result,
			@RequestHeader("X-User-Id") Integer userId) {
		return new CommonVO<Object>(shopService.addShop(dto,userId));
	}
	
}
