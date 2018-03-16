package com.openplan.server.web.bean.advisor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openplan.server.web.error.exception.entity.ExceptionEntity;
import com.openplan.server.web.error.factory.ExceptionStragetyFactory;
import com.openplan.server.web.error.stragety.ExceptionStragety;

@ControllerAdvice
@ConditionalOnProperty(prefix = "server.core.web", name = "error.advisor.enable", matchIfMissing = true)
public class GlobalErrorAdvisor {
	// server.core.web.error.advisor.enable
	
	private static final Logger LOG = LoggerFactory.getLogger(GlobalErrorAdvisor.class);
	
	
	@ResponseBody
    @ExceptionHandler(value = Exception.class)
    public final ExceptionEntity defaultErrorHandler(Exception ex) {
		if (LOG.isWarnEnabled()) {
			LOG.warn("Exception Stack:", ex);
		}
		ExceptionStragety exceptionStragety = ExceptionStragetyFactory.build(ex);
    	ExceptionEntity error = exceptionStragety.process();
        return error;
    }

}
