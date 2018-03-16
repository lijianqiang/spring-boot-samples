package com.openplan.server.web.error.stragety;

import org.springframework.http.HttpStatus;

import com.openplan.server.web.error.exception.InternalRestfulException;
import com.openplan.server.web.error.exception.entity.ExceptionEntity;

public class InternalRestfulExceptionStragety implements ExceptionStragety {
	
	private InternalRestfulException exception;
	
	public InternalRestfulExceptionStragety(InternalRestfulException exception) {
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
