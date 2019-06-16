package com.lphr.entity;


import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name="t_menu")
public class Menu extends BaseEntity {
	
    private Integer parentId;

    private String scode;

    private String menuName;

    private String iconCls;

    private Integer menuSort;

    private String menuPath;

    private Integer itype;



}