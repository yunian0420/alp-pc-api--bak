package com.lphr.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.lphr.BaseMapper;
import com.lphr.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

	/**
	 * 根据用户名或手机号码查找用
	 */
	User findByUsername(String username);

}