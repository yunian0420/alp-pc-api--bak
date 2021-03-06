package com.lphr.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Env {
	
	/**版本编号*/
	public static String verCode;
	
	public static String envName;
	
	public static Boolean isProduct;
	/**是否验证签名*/
	public static Boolean isVerifySign;
	
	/**是否验证登录*/
	public static Boolean isAuthLogin;
	
	/**是否发送日志到MQ*/
	public static Boolean isSendMQ;
	
	/**发送日志队列的名称*/
	public static String logQueueName;
	
	/**业务处理队列的名称*/
	public static String businessQueueName;

	@Value("${env.verCode}")
	public void setVerCode(String verCode){
		Env.verCode = verCode;
	}
	
	@Value("${env.envName}")
	public void setEnvName(String envName){
		Env.envName = envName;
	}
	
	@Value("${env.isProduct}")
	public void setProduct(Boolean isProduct){
		Env.isProduct = isProduct;
	}
	
	@Value("${env.isVerifySign}")
	public void setVerifySign(Boolean isVerifySign){
		Env.isVerifySign = isVerifySign;
	}
	
	@Value("${env.isAuthLogin}")
	public void setAuthLogin(Boolean isAuthLogin){
		Env.isAuthLogin = isAuthLogin;
	}
	
	@Value("${env.isSendMQ}")
	public void setSendMQ(Boolean isSendMQ){
		Env.isSendMQ = isSendMQ;
	}
	
	@Value("${env.logQueueName}")
	public void setLogQueueName(String logQueueName){
		Env.logQueueName = logQueueName;
	}

	@Value("${env.businessQueueName}")
	public void setBusinessQueueName(String businessQueueName){
		Env.businessQueueName = businessQueueName;
	}

}
