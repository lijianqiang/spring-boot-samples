package com.openplan.server.web.error.stragety;

import org.springframework.http.HttpStatus;

import com.openplan.server.web.error.exception.ServerDefinedException;
import com.openplan.server.web.error.exception.entity.ExceptionEntity;

public class ServerDefinedExceptionStragety implements ExceptionStragety {
	
	private ServerDefinedException exception;
	
	public ServerDefinedExceptionStragety(ServerDefinedException exception) {
		this.exception = exception;
	}

	@Override
	public ExceptionEntity process() {
		ExceptionEntity error = new ExceptionEntity();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setCode(exception.getErrorCode());
    	error.setException(exception);
        return error;
	}

}
