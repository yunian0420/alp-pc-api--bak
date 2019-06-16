package com.lphr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.lphr.config.version.CustomRequestMappingHandlerMapping;
import com.lphr.interceptor.LoginInterceptor;


/**
 * 配置类
 * 
 * @author 喻聪
 * @date   2018-01-26
 */
@Configuration
@ServletComponentScan 
//@MapperScan("com.lphr.mapper")
public class MainConfig extends WebMvcConfigurationSupport {

	@Autowired  
	private LoginInterceptor loginInterceptor;  
	
	/** 
     * 拦截器配置 
     */  
    @Override  
    public void addInterceptors(InterceptorRegistry registry) {  
        // 注册监控拦截器  
        registry.addInterceptor(loginInterceptor)  
                .addPathPatterns("/**")  
         .excludePathPatterns("/configuration/ui");
    }  
    
    @Bean
    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping handlerMapping = new CustomRequestMappingHandlerMapping();
        handlerMapping.setOrder(0);
        handlerMapping.setInterceptors(getInterceptors());
        return handlerMapping;
    }
    
	

	
}
