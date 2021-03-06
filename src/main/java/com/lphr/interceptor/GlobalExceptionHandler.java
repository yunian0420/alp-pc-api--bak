
package com.lphr.interceptor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.java.common.log.model.ServerExceptionLog;
import com.lphr.constants.ConfigConsts;
import com.lphr.constants.Env;
import com.lphr.constants.GlobalLog;
import com.lphr.enums.CodeEnum;
import com.lphr.exception.BusinessException;
import com.lphr.exception.ParameterIllegalException;
import com.lphr.exception.ServerInternalException;
import com.lphr.producer.LogDirectProducer;
import com.lphr.util.ResponseUtil;
import com.lphr.vo.ExceptionVO;

/**
 * 异常处理大管家
 * 
 * @author 喻聪
 * @date   2017-10-09
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final String MESSAGE_SERVER_EXCEPTION = "服务器内部错误";
	private static final String MESSAGE_BUSINESS_EXCEPTION = "业务处理失败";
	private static final String MESSAGE_PARAMTER_ILLEGAL  = "请求参数不合法";
	private static final String MESSAGE_METHOD_ILLEGAL  = "请求方式不支持";
	
	@Autowired
	private LogDirectProducer logDirectProducer;
	
	/**
	 * 不支持的请求方式
	 * 
	 * @author 喻聪
	 * 
	 * @param response
	 * @param ex
	 */
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public void handleException(HttpServletRequest request, HttpServletResponse response,HttpRequestMethodNotSupportedException e) {
		ExceptionVO returnVO = new ExceptionVO();
		returnVO.setCode(CodeEnum.CLIENT_ERROR.getCode());
		returnVO.setMessage(MESSAGE_METHOD_ILLEGAL);
		returnVO.setErrorCode(400);
		returnVO.setErrorMsg(e.getMessage());
		GlobalLog.MY_LOGGER.warn("不支持的请求方式",e);
		recordLog(request,response,e);
		ResponseUtil.write(response, returnVO);
	}
	
	/**
	 * 处理业务异常
	 * 
	 * @author 喻聪
	 * 
	 * @param response
	 * @param ex
	 */
	@ExceptionHandler(value = ParameterIllegalException.class)
	public void handleException(HttpServletRequest request,HttpServletResponse response,ParameterIllegalException e) {
		ExceptionVO returnVO = new ExceptionVO();
		returnVO.setCode(CodeEnum.CLIENT_ERROR.getCode());
		returnVO.setMessage(MESSAGE_PARAMTER_ILLEGAL);
		returnVO.setErrorCode(e.getErrorCode());
		returnVO.setErrorMsg(e.getMessage());
		GlobalLog.MY_LOGGER.warn("请求参数不合法",e);
		recordLog(request,response,e);
		ResponseUtil.write(response, returnVO);
	}
	
	/**
	 * 处理运行时异常
	 * 
	 * @author 喻聪
	 * 
	 * @param response
	 * @param ex
	 */
	@ExceptionHandler(value = RuntimeException.class)
	public void handleException(HttpServletRequest request,HttpServletResponse response,RuntimeException e) {
		ExceptionVO returnVO = new ExceptionVO();
		returnVO.setCode(CodeEnum.SERVER_ERROR.getCode());
		returnVO.setErrorCode(501);
		returnVO.setMessage(MESSAGE_SERVER_EXCEPTION);
		returnVO.setErrorMsg(e.getMessage());
		GlobalLog.MY_LOGGER.error("服务器内部异常",e);
		recordLog(request,response,e);
		//pushMsg(e);
		ResponseUtil.write(response, returnVO);
	}
	
	/**
	 * 处理运行时异常
	 * 
	 * @author 喻聪
	 * 
	 * @param response
	 * @param ex
	 */
	@ExceptionHandler(value = Exception.class)
	public void handleException(HttpServletRequest request,HttpServletResponse response,Exception e) {
		ExceptionVO returnVO = new ExceptionVO();
		returnVO.setCode(CodeEnum.SERVER_ERROR.getCode());
		returnVO.setMessage(MESSAGE_SERVER_EXCEPTION);
		GlobalLog.MY_LOGGER.error("服务器内部异常",e);
		returnVO.setErrorCode(500);
		returnVO.setErrorMsg(e.getMessage());
		recordLog(request,response,e);
		//pushMsg(e);
		ResponseUtil.write(response, returnVO);
	}
	
	/**
	 * 处理服务器内部异常
	 * 
	 * @author 喻聪
	 * @param response
	 * @param ex
	 */
	@ExceptionHandler(value = ServerInternalException.class)
	public void handleServer(HttpServletRequest request,HttpServletResponse response,ServerInternalException e) {
		ExceptionVO returnVO = new ExceptionVO();
		returnVO.setCode(CodeEnum.SERVER_ERROR.getCode());
		returnVO.setMessage(MESSAGE_SERVER_EXCEPTION);
		returnVO.setErrorCode(e.getErrorCode());
		returnVO.setErrorMsg(e.getMessage());
		GlobalLog.MY_LOGGER.error("服务器内部异常",e);
		recordLog(request,response,e);
		//pushMsg(e);
		ResponseUtil.write(response, returnVO);
	}

	/**
	 * 处理业务异常
	 *
	 * @author 喻聪
	 * @param response
	 * @param ex
	 */
	@ExceptionHandler(value = BusinessException.class)
	public void handleServer(HttpServletRequest request,HttpServletResponse response,BusinessException e) {
		ExceptionVO returnVO = new ExceptionVO();
		returnVO.setCode(CodeEnum.BUSINESS_ERROR.getCode());
		returnVO.setMessage(MESSAGE_BUSINESS_EXCEPTION);
		returnVO.setErrorCode(e.getErrorCode());
		returnVO.setErrorMsg(e.getMessage());
		GlobalLog.MY_LOGGER.warn("业务处理失败",e);
		recordLog(request,response,e);
		//pushMsg(e);
		ResponseUtil.write(response, returnVO);
	}
	
	private void recordLog(HttpServletRequest request,HttpServletResponse response,Exception e) {
		
		// 当rabbitMQ宕机等各种原因不能用时，可将isSendMQ设置为false
		if(Env.isSendMQ) {
			ServerExceptionLog log = new ServerExceptionLog();
			log.setDeviceType(request.getHeader(ConfigConsts.DEVICE_TYPE_KEY));
			log.setUserId(request.getHeader(ConfigConsts.X_USER_ID_KEY));
			log.setAppVersion(request.getHeader(ConfigConsts.APP_VERSION));
	        log.setCreateTime(new Date());
	        log.setClientIP(request.getRemoteAddr());
	        log.setRequestUrl(request.getRequestURI());
			log.setStackTrace(getStackTrace(e));
			log.setErrMsg(e.getMessage());		
			log.setServerIP(request.getLocalAddr());
			String method = request.getMethod();
			log.setMethod(method);
			logDirectProducer.produceExceptionLog(log);
		}
		GlobalLog.MY_LOGGER.error("YYCC：日志需要落地",e);
	}
	
	public static String getStackTrace(Throwable throwable) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			throwable.printStackTrace(pw);
			return sw.toString();
		} finally {
			pw.close();
		}
	}
	
	//短信通知
	/*private void pushMsg(Exception exception) {
		
		if(!Env.isProduct) {
			String key = exception.getClass().getSimpleName();
			String msg = redisTemplate.opsForValue().get(key);
			//异常信息
			String message = exception.getMessage();
			if(msg == null) {
				//需要改成邮件提醒
				MandaoSmsUtil.send("15102123715", message);
				redisTemplate.opsForValue().set(key,message);
				redisTemplate.expire(key, 24, TimeUnit.HOURS);//缓存24小时
			} 
		}
	}
	*/

	
}
