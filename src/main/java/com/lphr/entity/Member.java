package com.lphr.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name="t_member")
public class Member extends BaseEntity {

    private Integer userId;
    private String memberNo;
    private Integer shopId;
    private Integer employeeId;
    private Integer money;
    private BigDecimal totalCostMoney;
    private Integer totalCostTimes;
    private Date lastCostTime;
    private Integer points;
    private String memberLevel;
    private BigDecimal consumeAverage;

}