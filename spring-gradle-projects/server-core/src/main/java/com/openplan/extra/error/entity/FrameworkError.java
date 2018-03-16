package com.openplan.extra.error.entity;

import com.openplan.server.protocol.exception.error.GlobalError;

public final class FrameworkError {
	
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
	
	public FrameworkError(Integer code, Exception exception) {
		this.code = code;
		this.exception = exception;
	}

	public FrameworkError() {
		this(GlobalError.FRAMEWORK_ERROR, null);
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
