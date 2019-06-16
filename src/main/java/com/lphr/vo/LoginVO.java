package com.lphr.vo;

import lombok.Data;

@Data
public class LoginVO {
	private Integer userId;
	private String nickName;
	private String phone;
	private String tokenId;
}
