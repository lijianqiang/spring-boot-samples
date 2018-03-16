package com.openplan.server.web.error.exception;

import com.openplan.server.protocol.exception.DefinedException;
import com.openplan.server.protocol.exception.error.GlobalError;

public class ServerDefinedException extends DefinedException {

	private static final long serialVersionUID = 8241747723232910227L;
	
	private int errorCode;

	public ServerDefinedException() {
		super();
		this.errorCode = GlobalError.UNDEFINED_ERROR;
	}

	public ServerDefinedException(int code, String message) {
		super(message);
		this.errorCode = code;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	

}
