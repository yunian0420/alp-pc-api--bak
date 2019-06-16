package com.lphr.dto.member;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @author: YN
 * @Date: 2019年4月28日下午2:53:34
 *
 */
@Data
public class AddMemberLevelDTO {

	private String sname;

    private String level;

    private Integer experienceNum;

    private Byte flagSpecial;
    
    private BigDecimal pointsMultiple;
    
    private Integer sortNum;
}
