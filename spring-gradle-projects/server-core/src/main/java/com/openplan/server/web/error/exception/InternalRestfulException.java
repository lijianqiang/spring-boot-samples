package com.openplan.server.web.error.exception;

import com.openplan.server.protocol.exception.DefinedException;
import com.openplan.server.protocol.exception.error.GlobalError;

public class InternalRestfulException extends DefinedException {

	private static final long serialVersionUID = 8241747723232910227L;
	
	private int errorCode;

	public InternalRestfulException() {
		super();
		this.errorCode = GlobalError.INTERNAL_ERROR;
	}

	public InternalRestfulException(int code, String message) {
		super(format(message));
		this.errorCode = code;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	private static String format(String message) {
		StringBuilder sb = new StringBuilder();
		sb.append("remote[").append(message).append("]");
		return sb.toString();
	}

}
