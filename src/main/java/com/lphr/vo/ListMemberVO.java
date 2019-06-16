package com.lphr.vo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * @author: YN
 * @Date: 2019年4月28日下午1:34:03
 *
 */
@Data
public class ListMemberVO {
	
	private Integer id;
	
	private Integer userId;
	private String phone;

    private Integer memberNo;

    private Integer shopId;
    private String shopName;

    private Integer employeeId;
    private String employeeName;

    private BigDecimal money;
    private BigDecimal totalCostMoney;

    private Integer totalCostTimes;

    private Date lastCostTime;

    private Integer points;

    //暂时不用
    private String memberLevel;
    private BigDecimal consumeAverage;
}
