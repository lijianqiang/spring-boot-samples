package com.openplan.server.web.error.factory;

import com.openplan.server.web.error.exception.InternalRestfulException;
import com.openplan.server.web.error.exception.ServerDefinedException;
import com.openplan.server.web.error.stragety.DefaultExceptionStragety;
import com.openplan.server.web.error.stragety.ExceptionStragety;
import com.openplan.server.web.error.stragety.InternalRestfulExceptionStragety;
import com.openplan.server.web.error.stragety.NullPointerExceptionStragety;
import com.openplan.server.web.error.stragety.ServerDefinedExceptionStragety;

public class ExceptionStragetyFactory {

	public static final ExceptionStragety build(Exception e) {
		
		if (e instanceof ServerDefinedException) {
			return new ServerDefinedExceptionStragety((ServerDefinedException) e);
		}
		
		if (e instanceof InternalRestfulException) {
			return new InternalRestfulExceptionStragety((InternalRestfulException) e);
		}
		
		if (e instanceof NullPointerException) {
			return new NullPointerExceptionStragety((NullPointerException) e);
		}
		
		return new DefaultExceptionStragety(e);
	}

}
