package com.lphr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lphr.BaseMapper;
import com.lphr.dto.member.ListMemberDTO;
import com.lphr.entity.Member;
import com.lphr.vo.ListMemberVO;


@Mapper
public interface MemberMapper extends BaseMapper<Member>{

	/**
	 * @author: YN
	 * @Date: 2019年4月28日下午1:57:15
	 *
	 */ 
	List<ListMemberVO> findByPage(ListMemberDTO dto);
}