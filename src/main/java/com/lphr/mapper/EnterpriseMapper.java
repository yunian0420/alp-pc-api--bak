package com.lphr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lphr.BaseMapper;
import com.lphr.dto.enterprise.ListEnterpriseDTO;
import com.lphr.entity.Enterprise;
import com.lphr.vo.ListEnterpriseVO;

@Mapper
public interface EnterpriseMapper extends BaseMapper<Enterprise> {

	List<ListEnterpriseVO> findByPage(ListEnterpriseDTO dto);

}