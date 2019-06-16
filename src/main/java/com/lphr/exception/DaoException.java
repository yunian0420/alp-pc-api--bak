package com.lphr.exception;

public class DaoException extends RuntimeException {
	private static final long serialVersionUID = 7977898505585831130L;
	private int errorCode;
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public DaoException(int errorCode, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
	}
	
}
