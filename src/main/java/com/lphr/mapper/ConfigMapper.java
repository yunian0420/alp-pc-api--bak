package com.lphr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lphr.BaseMapper;
import com.lphr.entity.Config;
import com.lphr.vo.ListConfigVO;


@Mapper
public interface ConfigMapper extends BaseMapper<Config> {

	
	/**
	 * 获取所有的配置 
	 * 
	 * @author  YN
	 * @date    2019-4-24
	 */ 
	List<ListConfigVO> findAll(int i);


}