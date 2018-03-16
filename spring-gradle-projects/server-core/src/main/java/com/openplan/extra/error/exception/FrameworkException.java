package com.openplan.extra.error.exception;

import com.openplan.server.protocol.exception.error.GlobalError;

public class FrameworkException extends RuntimeException {

	private static final long serialVersionUID = 623379646603016408L;
	
	private int errorCode;

	public FrameworkException() {
		super();
		this.errorCode = GlobalError.FRAMEWORK_ERROR;
	}

	public FrameworkException(String message) {
		super(message);
		this.errorCode = GlobalError.FRAMEWORK_ERROR;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
