package com.lphr.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.java.common.log.constant.LogConstants;
import com.java.common.log.model.BusinessAbnormalLog;
import com.lphr.annotations.Auth;
import com.lphr.constants.ConfigConsts;
import com.lphr.constants.Env;
import com.lphr.constants.GlobalLog;
import com.lphr.constants.Token;
import com.lphr.enums.CodeEnum;
import com.lphr.manager.ConstsManager;
//import com.lphr.manager.ConstsManager;
import com.lphr.manager.TokenManager;
import com.lphr.producer.LogDirectProducer;
import com.lphr.util.FastJsonUtil;
import com.lphr.vo.CommonVO;

/**
 * 用户登录拦截器
 * 
 * @author 喻聪
 * @date   2018-01-25
 */
@Component
public class LoginInterceptor implements HandlerInterceptor{

	@Autowired
	private TokenManager tokenManager;
	
	@Autowired
	private ConstsManager constsManager;
	
	@Autowired
	private LogDirectProducer logDirectProducer;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		if(Env.isAuthLogin) {
			
			if(request.getMethod().equals("OPTIONS")) {
		    	return true;
		    }
			
			final HandlerMethod handlerMethod = (HandlerMethod) handler;  
	        final Method method = handlerMethod.getMethod();  
	        final Class<?> clazz = method.getDeclaringClass();  
	        if (clazz.isAnnotationPresent(Auth.class) ||   method.isAnnotationPresent(Auth.class)) {  
	           
	        	//从header中得到token
	    		String tokenId = request.getHeader(ConfigConsts.X_AUTH_TOKEN_KEY);
	    		String userId = request.getHeader(ConfigConsts.X_USER_ID_KEY);
	    		//验证token
	    		Token token = tokenManager.getToken(tokenId,userId);
	    		if (tokenManager.checkToken(token)) {
	    			return true;
	    		} else {
	    			GlobalLog.MY_LOGGER.warn("token验证失败,token:" + userId + "_" + tokenId);
	    			CommonVO<Object> commonVO = new CommonVO<Object>();
	    			response.setContentType("application/json; charset=utf-8");
	    			commonVO.setCode(CodeEnum.AUTH_FAIL.getCode());
	    			commonVO.setMessage(constsManager.get("unLogin"));// 定义成失败名称常量
	    			response.getWriter().print(FastJsonUtil.toJson(commonVO));
	    			
	    			if(Env.isSendMQ) {
	    				GlobalLog.MY_LOGGER.error("登录验证失败，需要将日志落地。。。。");
	    				BusinessAbnormalLog log = new BusinessAbnormalLog();
	    				log.setErrorLevel(LogConstants.Level.INFO);
	    				log.setBusinessDesc("登陆验证");
	    				log.setErrorDesc("token验证失败");
	    				log.setUserId(userId);
	    				//log.setRequestUrl(request.getRequestURI());
	    				//log.setAppVersion(request.getHeader(ConfigConsts.APP_VERSION));
	    				//log.setDeviceType(request.getHeader(ConfigConsts.DEVICE_TYPE_KEY));
	    				logDirectProducer.produceAbnormalLog(log);
	    			}
	    			return false;
	    		}
	        }  
		}
		
        return true;
	}
	
	

	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

	public TokenManager getTokenManager() {
		return tokenManager;
	}

	public void setTokenManager(TokenManager tokenManager) {
		this.tokenManager = tokenManager;
	}
	
	

}

