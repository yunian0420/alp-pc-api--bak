package com.lphr.entity;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name="t_shop")
public class Shop extends BaseEntity {

    private String shopName;

    private String contactPerson;

    private String contactPhone;

    private String address;

    private Integer provinceId;

    private String provinceName;

    private Integer cityId;

    private String cityName;

    private Integer areaId;

    private String areaName;

}