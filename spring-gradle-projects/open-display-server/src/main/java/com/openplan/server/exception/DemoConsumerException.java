package com.openplan.server.exception;

import com.openplan.server.web.error.exception.ServerDefinedException;

public class DemoConsumerException extends ServerDefinedException {
	
	private static final long serialVersionUID = 8262622942019659909L;

	public DemoConsumerException() {
		super();
	}

	public DemoConsumerException(int code, String message) {
		super(code, message);
	}

	public DemoConsumerException(ErrorCode error) {
		this(error.getCode(), error.getMessage());
	}

}
