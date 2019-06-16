package com.lphr.entity;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name="t_role")
public class Role extends BaseEntity {

    private String roleCode;

    private String roleName;

    private String roleDesc;

}