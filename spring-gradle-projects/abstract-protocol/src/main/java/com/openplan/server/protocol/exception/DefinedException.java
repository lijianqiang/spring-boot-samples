package com.openplan.server.protocol.exception;

public class DefinedException extends RuntimeException {
	
	private static final long serialVersionUID = 4535834653889117595L;
	
	public DefinedException(String message) {
		super(message);
	}
	
	public DefinedException() {
		super();
	}

}
