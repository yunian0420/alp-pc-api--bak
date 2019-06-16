package com.lphr.entity;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name="t_enterprise")
public class Enterprise extends BaseEntity {

    private Integer userId;
    private String enterpriseName;
    private String principal;
    private String contactWay;

}