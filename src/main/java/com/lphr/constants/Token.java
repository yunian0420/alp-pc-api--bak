package com.lphr.constants;

import com.lphr.util.FastJsonUtil;

import lombok.Data;

@Data
public class Token {
	private String tokenId;
	private String userId;

	public Token() {
		
	}
	
	public Token(String tokenId,String userId) {
		this.tokenId = tokenId;
		this.userId = userId;
	}
	
    public String toString() {
    	return FastJsonUtil.toJson(this);
    }
    
  

}
