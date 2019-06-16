package com.lphr.vo;

import java.util.List;

import lombok.Data;

/**
 * @author: YN
 * @Date: 2019年4月24日下午3:22:02
 * @TODO: 
 * @param: 
 * @return: 
 */
@Data
public class ListConfigVO {
	private Integer id;
	private Integer parentId;
    private String sname;
    private String sysKey;
    private String sysValue;
    private String sysDes;
    
    private List<ListConfigVO> children;
}
