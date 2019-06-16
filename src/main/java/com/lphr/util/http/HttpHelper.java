package com.lphr.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lphr.constants.GlobalLog;



public class HttpHelper {

	/**
	 * 
	 * @Title: getIpAddr   
	 * @Description: 获取登录用户远程主机ip地址
	 * @author   喻聪
	 * @date 2017年9月29日 上午11:58:08   
	 * @param request
	 * @return      
	 * @return String      
	 * @throws
	 */
	public static  String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static void setResponse(HttpServletResponse response, int value,
			Object object) {
		response.setStatus(value);
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=utf-8");
			out = response.getWriter();
			out.println(object);
		} catch (IOException e) {
			GlobalLog.MY_LOGGER.debug("io输出异常", e);
		} finally {
			if(out != null) {
				out.flush();
				out.close();
			}
		}
		
		
	}
	
	
	/**
	 * 获取请求体中的字符串(POST) 
	 */
	public static String getBodyData(BufferedReader reader) {
		StringBuffer data = new StringBuffer();
		String line = null;
		try {
			while (null != (line = reader.readLine()))
				data.append(line);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		
		}
		return data.toString();
	}
	
}
