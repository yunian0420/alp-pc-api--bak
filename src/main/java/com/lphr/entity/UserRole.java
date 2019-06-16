package com.lphr.entity;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name="t_user_role")
public class UserRole extends BaseEntity {

    private Integer userId;

    private Integer roleId;

}