package com.lphr.exception;

public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private int errorCode;
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public BusinessException(int errorCode, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
	}
}
