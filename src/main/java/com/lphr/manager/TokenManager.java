package com.lphr.manager;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lphr.constants.ConfigConsts;
import com.lphr.constants.Token;
import com.lphr.util.StringUtil;



@Component
public class TokenManager {

	@Autowired
	private RedisManager redisManager;
	
	public Token createToken(String userId) {
		Token token = new Token();
		token.setTokenId(StringUtil.getSimpleUUID());
		token.setUserId(userId);
		redisManager.setObject(userId, token.getTokenId());
		redisManager.expire(userId, ConfigConsts.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
		return token;
	}
	
	public Token getToken(String tokenId,String userId) {
		if (tokenId == null || tokenId.length() == 0) {
            return null;
        }
		if (userId == null || userId.length() == 0) {
            return null;
        }
        return new Token(tokenId, userId);
	}
	
	public boolean checkToken(Token token) {
		if(token == null) {
			return false;
		}
		String tokenValue = (String)redisManager.getObject(token.getUserId());
		if(tokenValue == null) {
			return false;
		}
		if(token.getTokenId().equals(tokenValue) ) {
			//如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
			redisManager.expire(token.getUserId(), ConfigConsts.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
			return true;
		}
		return false;
	}
	
	 public void deleteToken(String userId) {
		 redisManager.delete(userId);
     }
	
}
