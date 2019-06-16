package com.lphr.dto.member;

import lombok.Data;

/**
 * @author: YN
 * @Date: 2019年4月28日下午2:24:56
 *
 */
@Data
public class AddMemberDTO {
	
	private String nickName;
	private String password;
	private String phone;
	private String sex;
	private String memberNo;
}
