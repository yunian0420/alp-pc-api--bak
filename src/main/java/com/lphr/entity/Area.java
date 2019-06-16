package com.lphr.entity;


import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name="t_area")
public class Area extends BaseEntity {

    private Integer parentId;

    private String sname;

    private Byte itype;

    private Byte flagHot;

}