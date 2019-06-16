package com.lphr.constants;

/**
 * 系统常量
 */
public interface ConfigConsts {

	/**系统默认字符集*/
	String  DEFAUT_ENCODE = "UTF-8";
	
	String X_AUTH_TOKEN_KEY = "X-Auth-Token";
	 
	String X_USER_ID_KEY = "X-User-Id";
	
	String DEVICE_TYPE_KEY = "Device-Type";
	
	String APP_VERSION = "App-Version";
	
	/**系统token超时时间为7*24小时*/
	int TOKEN_EXPIRES_HOUR = 7*24;
	
	/**系统参数缓存时间*/
    int SYSTEM_CONFIG_CACHE_TIME = 7*24;
	
	/**客户端请求签名的KEY*/
	String APP_KEY = "123456";
	 
}
