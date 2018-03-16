package com.openplan.server.web.error.stragety;

import org.springframework.http.HttpStatus;

import com.openplan.server.protocol.exception.error.GlobalError;
import com.openplan.server.web.error.exception.entity.ExceptionEntity;

public class DefaultExceptionStragety implements ExceptionStragety {
	
	private Exception exception;
	
	public DefaultExceptionStragety(Exception exception) {
		this.exception = exception;
	}

	@Override
	public ExceptionEntity process() {
		ExceptionEntity error = new ExceptionEntity();
    	error.setStatus(HttpStatus.BAD_REQUEST.value());
    	error.setCode(GlobalError.UNDEFINED_ERROR);
    	error.setException(exception);
        return error;
	}

}
