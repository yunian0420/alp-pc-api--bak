package com.lphr.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lphr.annotations.ApiVersion;
import com.lphr.dto.config.AddConfigDTO;
import com.lphr.dto.config.DeletelConfigDTO;
import com.lphr.dto.config.UpdateConfigDTO;
import com.lphr.service.ConfigService;
import com.lphr.vo.CommonVO;
import com.lphr.vo.ListConfigVO;

@RestController
@RequestMapping("{version}/config")
public class ConfigController {

	@Autowired
	private ConfigService configService;
	
	/**
	 * @author: YN
	 * @Date: 2019年4月24日下午4:26:38
	 * @TODO: 查询所有配置项数据
	 * ******* 其格式需要注意如何实现
	 * @param: List<ListConfigVO> children
	 * @return: 注意数据回显的的方式
	 */
	@ApiVersion(1)
	@GetMapping("listAll")
	public CommonVO<List<ListConfigVO>> list() {
		return new CommonVO<>(configService.list());
	}
	
	/**
	 * @author: YN
	 * @Date: 2019年4月24日下午4:29:00
	 * @TODO: 添加一条配置项数据，保存到redis中
	 * @param: private String sysKey; 其key值应验证不能为纯数字，并不能重复。
	 * @return: 
	 */
	@ApiVersion(1)
	@PostMapping("add")
	public CommonVO<Object> addConfig(@RequestBody @Valid AddConfigDTO dto,BindingResult result) {
		return configService.addConfig(dto);
	}
	
	/**
	 * @author: YN
	 * @Date: 2019年4月24日下午4:31:57
	 * @TODO: 同添加注意事项
	 * @param: 
	 * @return:
	 */
	@PostMapping("update")
	public CommonVO<Object> update(@RequestBody @Valid UpdateConfigDTO dto,BindingResult result) {
		return configService.updateConfig(dto);
	}

	
	/**
	 * 
	 * @author: YN
	 * @Date: 2019年4月24日下午3:42:16
	 * @TODO: 
	 * @param: 
	 * @return:
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public CommonVO<Object> delete(@RequestBody DeletelConfigDTO dto) {
		configService.deleteConfig(dto.getId());
		return new CommonVO<Object>();
	}
	
}
