package com.lphr.entity;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name="t_menu_role")
public class MenuRole extends BaseEntity {
    

    private Integer menuId;

    private Integer roleId;

    

}