package com.lphr.entity;

import java.util.Date;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name="t_points_change")
public class PointsChange extends BaseEntity {

    private Integer userId;

    private Date changeTime;

    private Integer changeNum;

    private String reason;

    private Byte changeType;

}