package com.lphr.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lphr.dto.member.AddMemberLevelDTO;
import com.lphr.dto.member.ListMemberLevelDTO;
import com.lphr.dto.member.updateMemberLevelDTO;
import com.lphr.entity.MemberLevel;
import com.lphr.enums.StateEnum;
import com.lphr.mapper.MemberLevelMapper;
import com.lphr.util.BeanMapper;
import com.lphr.vo.DataTableVO;
import com.lphr.vo.ListMemberLevelVO;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @author: YN
 * @Date: 2019年4月28日下午2:50:39
 *
 */

@Service
public class MemberLevelService {

	@Autowired
	private MemberLevelMapper memberLevelMapper;
	/**
	 * @author: YN
	 * @Date: 2019年4月28日下午2:51:37
	 *
	 */ 
	public DataTableVO<ListMemberLevelVO> findByPage(ListMemberLevelDTO dto) {
		Example example = new Example(MemberLevel.class);
		example.setOrderByClause("sort_num ASC");
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("state", StateEnum.VALID.getCode());
		criteria.andEqualTo("flagDelete", 0);
		
		PageHelper.startPage(dto.getPage(), dto.getSize());
		List<MemberLevel> entitys = memberLevelMapper.selectByExample(example);
		PageInfo<MemberLevel> page = new PageInfo<MemberLevel>(entitys);
		int pageSize  = page.getPageSize();
		long allCount = page.getTotal();
		int allPage = page.getPages();
		int currentPage = page.getPageNum();
		List<ListMemberLevelVO> VO = BeanMapper.mapList(entitys, ListMemberLevelVO.class);
		DataTableVO<ListMemberLevelVO> dataTable = new DataTableVO<>(pageSize, allCount, allPage, currentPage, VO);
		return dataTable;
	}

	/**
	 * @author: YN
	 * @Date: 2019年4月28日下午2:55:08
	 */ 
	public Object addMemberLevel(AddMemberLevelDTO dto, Integer userId) {
		MemberLevel level = BeanMapper.map(dto, MemberLevel.class);
		level.setCreateUser(userId);
		return memberLevelMapper.insertSelective(level);
	}

	/**
	 * @author: YN
	 * @Date: 2019年4月28日下午4:46:06
	 */ 
	public Object updateMemberLevel(@Valid updateMemberLevelDTO dto, Integer userId) {
		MemberLevel level = BeanMapper.map(dto, MemberLevel.class);
		level.setUpdateUser(userId);
		return memberLevelMapper.updateByPrimaryKeySelective(level);
	}

}
