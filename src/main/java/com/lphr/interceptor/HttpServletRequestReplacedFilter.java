package com.lphr.interceptor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import com.java.common.log.constant.LogConstants;
import com.java.common.log.model.HttpRequestLog;
import com.lphr.constants.ConfigConsts;
import com.lphr.constants.Env;
import com.lphr.constants.GlobalLog;
import com.lphr.producer.LogDirectProducer;
import com.lphr.util.FastJsonUtil;
import com.lphr.util.http.HttpHelper;
import com.lphr.util.http.HttpIpAddress;


/**
 * @author 喻聪
 *
 */
@WebFilter(urlPatterns = "/*", filterName = "requestFilter")
@Order(2)
public class HttpServletRequestReplacedFilter implements Filter {

	private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("druid","webjars","swagger","api-docs")));
	
	@Autowired
	private LogDirectProducer logDirectProducer;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		GlobalLog.MY_LOGGER.info("HttpServletRequestReplacedFilter初始化了");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		long startTime = System.currentTimeMillis();
		//如果是OPTIONS请求，不需要抓包
		HttpServletRequest req = (HttpServletRequest)request;
		String method = req.getMethod();
		if(method.equals("OPTIONS")) {
	    	return ;
	    }
		
		String requestURI = req.getRequestURI();
		GlobalLog.MY_LOGGER.info("查看请求的URL：" + requestURI);
        
		if (isIgore(requestURI)) {
			
            chain.doFilter(request, response);
        
        } else {
        	
        	BodyReaderHttpServletRequestWrapper	requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
			String threadName = Thread.currentThread().getName();
			GlobalLog.MY_LOGGER.info("startTime:" + startTime + ",trheadName:" + threadName);
			
			// 包装响应对象 resp 并缓存响应数据
			ResponseWrapper mResp = new ResponseWrapper((HttpServletResponse) response); 
			chain.doFilter(requestWrapper, mResp);
			
			String param = null;
			if (method.equalsIgnoreCase("GET")) {
				param = requestWrapper.getQueryString();
			} else {
				param = HttpHelper.getBodyData(requestWrapper.getReader());
			}
			
			//获取Http请求的出参
			GlobalLog.MY_LOGGER.info("查看入参:" + param);
			byte[] bytes = mResp.getBytes(); // 获取缓存的响应数据
			GlobalLog.MY_LOGGER.info("查看出参：" + new String(bytes, ConfigConsts.DEFAUT_ENCODE));
	
			//从header中得到token
			String deviceType = requestWrapper.getHeader(ConfigConsts.DEVICE_TYPE_KEY);
			String userId = requestWrapper.getHeader(ConfigConsts.X_USER_ID_KEY);
			String appVersion = requestWrapper.getHeader(ConfigConsts.APP_VERSION);
			HttpRequestLog log = new HttpRequestLog();
			log.setUserId(userId);
			log.setDeviceType(deviceType == null ? LogConstants.DeviceType.UNKONW : deviceType);
			log.setAppVersion(appVersion);
			log.setMethod(method);
			log.setRequestParam(param);
			String respData = null;
			if(bytes.length == 0) {
				respData = "{\"status\":404}";
			} else {
				respData = new String(bytes, ConfigConsts.DEFAUT_ENCODE);
			}
			log.setResponseData(FastJsonUtil.parseJsonObject(respData, Object.class));
			//获取客户端真实IP
			String clientIP = HttpIpAddress.getIpAddr(requestWrapper);
			log.setClientIP(clientIP);
			log.setRequestUrl(requestWrapper.getRequestURI());
			log.setServerIP(requestWrapper.getLocalAddr());
			long endTime = System.currentTimeMillis();
			long consumeTime = endTime - startTime;
			log.setConsumeTime(consumeTime);
			GlobalLog.MY_LOGGER.info("endTime:" + startTime + ",trheadName:" + threadName);
			
			// 当rabbitMQ宕机等各种原因不能用时，可将isSaveLog设置为false
			if(Env.isSendMQ) {
				logDirectProducer.produceRequestLog(log);
			}
			response.getOutputStream().write(bytes);
        }
	}

	@Override
	public void destroy() {

	}
	
	private boolean isIgore(String url) {
		for(String path : ALLOWED_PATHS) {
			if(url.contains(path) ) {
				return true;
			}
		}
		return false;
	}


}
