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
import com.lphr.dto.member.AddMemberLevelDTO;
import com.lphr.dto.member.ListMemberLevelDTO;
import com.lphr.dto.member.updateMemberLevelDTO;
import com.lphr.service.MemberLevelService;
import com.lphr.vo.CommonVO;
import com.lphr.vo.DataTableVO;
import com.lphr.vo.ListMemberLevelVO;


@RestController
@RequestMapping("{version}/memberLevel")
public class MemberLevelController {

	@Autowired
	private MemberLevelService memberLevelService;
	
	/**
	 * 查询登记列表
	 * @author: YN
	 * @Date: 2019年4月28日下午3:23:58
	 *
	 */
	@ApiVersion(1)
	@GetMapping("list")
	public CommonVO<DataTableVO<ListMemberLevelVO>> list(ListMemberLevelDTO dto) {
		return new CommonVO<DataTableVO<ListMemberLevelVO>>(memberLevelService.findByPage(dto));
	}
	
	/**
	 * 添加一条等级记录
	 * @author: YN
	 * @Date: 2019年4月28日下午3:24:15
	 */
	@ApiVersion(1)
	@PostMapping("add")
	public CommonVO<Object> addMember(@RequestBody @Valid AddMemberLevelDTO dto, BindingResult result,
			@RequestHeader("X-User-Id") Integer userId) {
		return new CommonVO<Object>(memberLevelService.addMemberLevel(dto,userId));
	}
	
	/**
	 * 更新一条等级记录
	 * @author: YN
	 * @Date: 2019年4月28日下午3:24:15
	 */
	@ApiVersion(1)
	@PostMapping("update")
	public CommonVO<Object> addMember(@RequestBody @Valid updateMemberLevelDTO dto, BindingResult result,
			@RequestHeader("X-User-Id") Integer userId) {
		return new CommonVO<Object>(memberLevelService.updateMemberLevel(dto,userId));
	}
}
