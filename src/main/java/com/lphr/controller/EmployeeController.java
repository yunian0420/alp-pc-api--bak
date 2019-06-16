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
import com.lphr.dto.shop.AddShopEmployeeDTO;
import com.lphr.dto.shop.ListEmployeeDTO;
import com.lphr.dto.shop.UpdateShopEmployeeDTO;
import com.lphr.service.EmployeeService;
import com.lphr.vo.CommonVO;
import com.lphr.vo.DataTableVO;
import com.lphr.vo.ListEmployeeVO;

@RestController
@RequestMapping("{version}/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@ApiVersion(1)
	@GetMapping("list")
	public CommonVO<DataTableVO<ListEmployeeVO>> list(ListEmployeeDTO dto) {
		return new CommonVO<DataTableVO<ListEmployeeVO>>(employeeService.findByPage(dto));
	}
	
	@ApiVersion(1)
	@PostMapping("add")
	public CommonVO<Object> addEmployee(@RequestBody @Valid AddShopEmployeeDTO dto, BindingResult result,
			@RequestHeader("X-User-Id") Integer userId) {
		return employeeService.addEmployee(dto,userId);
	}
	
	@ApiVersion(1)
	@PostMapping("update")
	public CommonVO<Object> updateEmployee(@RequestBody @Valid UpdateShopEmployeeDTO dto, BindingResult result,
			@RequestHeader("X-User-Id") Integer userId) {
		return employeeService.updateEmployee(dto,userId);
	}
}
