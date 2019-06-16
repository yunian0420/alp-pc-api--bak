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
import com.lphr.dto.enterprise.AddEnterpriseDTO;
import com.lphr.dto.enterprise.ListEnterpriseDTO;
import com.lphr.service.EnterpriseService;
import com.lphr.vo.BaseVO;
import com.lphr.vo.CommonVO;
import com.lphr.vo.DataTableVO;
import com.lphr.vo.ListEnterpriseVO;

@RestController
@RequestMapping("{version}/enterprise")
public class EnterpriseController {
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	/**
	 * 
	 * 主体列表
	 * 
	 * @author 喻聪
	 * @date   2019-05-01
	 */
	@ApiVersion(1)
	@GetMapping("list")
	public CommonVO<DataTableVO<ListEnterpriseVO>> list(ListEnterpriseDTO dto) {
		return new CommonVO<DataTableVO<ListEnterpriseVO>>(enterpriseService.findByPage(dto));
	}
	
	/**
	 * 新增主体
	 * 
	 * @author 喻聪
	 * @date   2019-05-01
	 */
	@ApiVersion(1)
	@PostMapping("add")
	public BaseVO addMember(@RequestBody @Valid AddEnterpriseDTO dto, BindingResult result,
			@RequestHeader("X-User-Id") Integer userId) {
		return enterpriseService.add(dto,userId);
	}
	
}
