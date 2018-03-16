package com.openplan.server.web.restful.factory;

import org.springframework.core.MethodParameter;
import org.springframework.http.server.ServerHttpResponse;

import com.openplan.extra.error.entity.FrameworkError;
import com.openplan.server.protocol.result.JsonResponse;
import com.openplan.server.web.error.exception.entity.ExceptionEntity;
import com.openplan.server.web.restful.stragety.ResponseStragety;
import com.openplan.server.web.restful.stragety.ReturnDirectResponseStragety;
import com.openplan.server.web.restful.stragety.ReturnErrorResponseStragety;
import com.openplan.server.web.restful.stragety.ReturnNormalResponseStragety;
import com.openplan.server.web.restful.stragety.ReturnNullResponseStragety;

public class ResponseStragetyFactory {
	
	@SuppressWarnings("rawtypes")
	public static ResponseStragety build(Object body, ServerHttpResponse response, MethodParameter returnType, boolean enableDebugRequest) {
		if (body == null) {
			return new ReturnNullResponseStragety(response, enableDebugRequest);
		}
		
		if (body instanceof FrameworkError) {
			ExceptionEntity entity = new ExceptionEntity();
			FrameworkError error = (FrameworkError) body;
			entity.setCode(error.getCode());
			entity.setException(error.getException());
			entity.setStatus(error.getStatus());
			return new ReturnErrorResponseStragety(entity, response, enableDebugRequest);
		}

		if (body instanceof ExceptionEntity) {
			return new ReturnErrorResponseStragety((ExceptionEntity) body, response, enableDebugRequest);
		}
		
		if (body instanceof JsonResponse) {
			return new ReturnDirectResponseStragety((JsonResponse) body, response, enableDebugRequest);
		}

		
		return new ReturnNormalResponseStragety(body, response, enableDebugRequest);
	}

}
