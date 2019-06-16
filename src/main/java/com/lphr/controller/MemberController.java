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
import com.lphr.dto.member.AddMemberDTO;
import com.lphr.dto.member.ListMemberDTO;
import com.lphr.service.MemberService;
import com.lphr.vo.BaseVO;
import com.lphr.vo.CommonVO;
import com.lphr.vo.DataTableVO;
import com.lphr.vo.ListMemberVO;

/**
 * @author: YN
 * @Date: 2019年4月28日下午1:25:42
 *
 */
@RestController
@RequestMapping("{version}/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@ApiVersion(1)
	@GetMapping("list")
	public CommonVO<DataTableVO<ListMemberVO>> list(ListMemberDTO dto) {
		return new CommonVO<DataTableVO<ListMemberVO>>(memberService.findByPage(dto));
	}
	
	@ApiVersion(1)
	@PostMapping("add")
	public BaseVO addMember(@RequestBody @Valid AddMemberDTO dto, BindingResult result,
			@RequestHeader("X-User-Id") Integer userId) {
		return memberService.addMember(dto,userId);
	}
}
