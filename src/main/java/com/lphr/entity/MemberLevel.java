package com.lphr.entity;

import java.math.BigDecimal;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name="t_member_level")
public class MemberLevel extends BaseEntity {

    private String sname;

    private String level;

    private String sdescription;

    private String iconUrl;

    private Integer sortNum;

    private Integer experienceNum;

    private Byte flagSpecial;

    private BigDecimal pointsMultiple;

}