package com.lphr.config.version;

import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.lphr.annotations.ApiVersion;

public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

	@Override
    protected RequestCondition<ApiVersionCondition> getCustomTypeCondition(Class<?> handlerType) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        return createCondition(apiVersion);
    }
 
    @Override
    protected RequestCondition<ApiVersionCondition> getCustomMethodCondition(Method method) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        return createCondition(apiVersion);
    }
     
    private RequestCondition<ApiVersionCondition> createCondition(ApiVersion apiVersion) {
        return apiVersion == null ? null : new ApiVersionCondition(apiVersion.value());
    }
}


