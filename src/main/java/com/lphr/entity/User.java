package com.lphr.entity;

import java.util.Date;

import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Table(name="t_user")
public class User extends BaseEntity {

    private String username;
    private String phone;
    private String cardNo;
    private String code;
    private Integer userType;
    private String password;
    private String email;
    private String headPic;
    private Integer loginNum;
    private Date lastLoginTime;
    private String lastLoginIp;
    private String nickName;
    private String realName;
    private String sex;
    private Date birthday;
    private Integer isTester;
    private String inviteCode;
    
}