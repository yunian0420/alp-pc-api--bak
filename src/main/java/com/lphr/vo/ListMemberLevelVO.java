package com.lphr.vo;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @author: YN
 * @Date: 2019年4月28日下午2:57:03
 *
 */
@Data
public class ListMemberLevelVO {

	private Integer id;
	
	private String sname;

    private String level;

    private Integer experienceNum;

    private Byte flagSpecial;
    
    private BigDecimal pointsMultiple;
    
    private Integer sortNum;
    
    //多余字段
    private String sdescription;
    private String iconUrl;

    
}
