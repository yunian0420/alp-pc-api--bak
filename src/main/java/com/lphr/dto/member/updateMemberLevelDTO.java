package com.lphr.dto.member;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author: YN
 * @Date: 2019年4月28日下午4:44:37
 *
 */
@Data
public class updateMemberLevelDTO {

	@NotNull(message="id必填")
	private Integer id;
	
	private String sname;

    private String level;

    private Integer experienceNum;

    private Byte flagSpecial;
    
    private BigDecimal pointsMultiple;
    
    private Integer sortNum;
}
