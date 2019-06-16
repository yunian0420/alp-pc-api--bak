package com.lphr.entity;


import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name="t_shop_employee")
public class ShopEmployee extends BaseEntity {

    private Integer userId;

    private String employeeNo;

    private Integer shopId;

}