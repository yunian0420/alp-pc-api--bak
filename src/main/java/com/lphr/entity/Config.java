package com.lphr.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "t_config")
public class Config {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    private Integer parentId;
    private String sname;
    private String sysKey;
    private String sysValue;
    private String sysDes;
    private Integer state;
    private Date createTime;
    private Date updateTime;
    private Integer updateUser;
    private Integer flagDel;
    private String memo;
    private Integer createUser;

}