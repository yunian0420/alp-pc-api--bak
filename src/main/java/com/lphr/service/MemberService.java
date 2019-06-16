package com.lphr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lphr.dto.member.AddMemberDTO;
import com.lphr.dto.member.ListMemberDTO;
import com.lphr.entity.Member;
import com.lphr.entity.ShopEmployee;
import com.lphr.entity.User;
import com.lphr.enums.UserTypeEnum;
import com.lphr.mapper.MemberMapper;
import com.lphr.mapper.ShopEmployeeMapper;
import com.lphr.mapper.UserMapper;
import com.lphr.util.MD5;
import com.lphr.vo.BaseVO;
import com.lphr.vo.DataTableVO;
import com.lphr.vo.ListMemberVO;

@Service
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ShopEmployeeMapper shopMapperEmployee;
	
	/**
	 * @author: YN
	 * @Date: 2019年4月28日下午1:31:16
	 *
	 */ 
	public DataTableVO<ListMemberVO> findByPage(ListMemberDTO dto) {
		
		PageHelper.startPage(dto.getPage(), dto.getSize());
		List<ListMemberVO> entitys = memberMapper.findByPage(dto);
		PageInfo<ListMemberVO> page = new PageInfo<ListMemberVO>(entitys);
		int pageSize  = page.getPageSize();
		long allCount = page.getTotal();
		int allPage = page.getPages();
		int currentPage = page.getPageNum();
		DataTableVO<ListMemberVO> dataTable = new DataTableVO<ListMemberVO>(pageSize, allCount, allPage, currentPage, entitys);
		return dataTable;
	}
	
	/**
	 * @author: YN
	 * @Date: 2019年4月28日下午2:30:15
	 * todo：先简单处理，后期加入逻辑
	 */ 
	public BaseVO addMember(AddMemberDTO dto, Integer userId) {
		User u = new User();
		u.setNickName(dto.getNickName());
		u.setPhone(dto.getPhone());
		u.setUserType(UserTypeEnum.CUSTOMER.getCode());
		u.setPassword(MD5.getMD5(dto.getPhone() + dto.getPassword()));
		userMapper.insertSelective(u);
		
		ShopEmployee employee = new ShopEmployee();
		employee.setUserId(userId);
		ShopEmployee shopEmployee = shopMapperEmployee.selectOne(employee);
		
		Member member = new Member();
		member.setUserId(u.getId());
		member.setCreateUser(userId);
		member.setShopId(shopEmployee.getShopId());
		member.setEmployeeId(userId);
		memberMapper.insertSelective(member);
		setMemberNo(shopEmployee.getShopId(),member.getId());
		return new BaseVO();
	}
	
	/*
	 * 设置员工会员卡卡号
	 * 
	 * @author 喻聪
	 * @date   2019-05-01
	 */
	private void setMemberNo(int shopId,int memberId) {
		String memberNo = String.format("%04d", shopId) + String.format("%06d", memberId);
		Member member = new Member();
		member.setId(memberId);
		member.setMemberNo(memberNo);
		memberMapper.updateByPrimaryKeySelective(member);
	}
	
	

}
