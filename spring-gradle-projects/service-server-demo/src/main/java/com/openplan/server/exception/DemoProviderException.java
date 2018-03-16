package com.openplan.server.exception;

import com.openplan.server.web.error.exception.ServerDefinedException;

public class DemoProviderException extends ServerDefinedException {
	
	private static final long serialVersionUID = 2029566772268876591L;

	public DemoProviderException() {
		super();
	}

	public DemoProviderException(int errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	public DemoProviderException(ErrorCode dataNotFound) {
		this(dataNotFound.getCode(), dataNotFound.getMessage());
	}

}
