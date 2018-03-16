package com.openplan.server.web.error.exception.entity;

public final class ExceptionEntity {
	
	/**
	 * Http Status
	 */
	private Integer status;
	
	/**
	 * Error Code
	 */
	private Integer code;
	
	/**
	 * Exception bean
	 */
	private Exception exception;
	
	public ExceptionEntity(Integer code, Exception exception) {
		this.code = code;
		this.exception = exception;
	}

	public ExceptionEntity() {
		this(null, null);
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
}
