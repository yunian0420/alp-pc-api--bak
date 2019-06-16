package com.lphr.manager;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.lphr.constants.ConfigConsts;
import com.lphr.entity.Config;
import com.lphr.exception.BusinessException;
import com.lphr.mapper.ConfigMapper;


/**
 * 常量管理类
 * 
 * 从redis里面去取，然后从msyql里面去取
 * 
 * @author 喻聪
 * @date   2018-01-29
 */
@Component
public class ConstsManager {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private ConfigMapper configMapper;
	
	public String get(String key) {
		
		String value = redisTemplate.opsForValue().get(key);
		if(value == null) {
			Config record = new Config();
			record.setState(1);
			record.setSysKey(key);
			Config config = configMapper.selectOne(record);
			if(config == null) {
				//发短信 send notify msg
				throw new BusinessException(501, "系统参数不存在,key:" + key);
			}
			value = config.getSysValue();
			if (value == null) {
				throw new BusinessException(501, "系统参数值为空,key:" + key);
			}
			redisTemplate.opsForValue().set(key,value);
			redisTemplate.expire(key, ConfigConsts.SYSTEM_CONFIG_CACHE_TIME, TimeUnit.HOURS);//缓存7天
		}
		return value;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
