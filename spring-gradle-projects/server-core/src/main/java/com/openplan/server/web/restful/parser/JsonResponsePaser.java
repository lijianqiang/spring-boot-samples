package com.openplan.server.web.restful.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openplan.server.protocol.exception.error.GlobalError;
import com.openplan.server.protocol.result.JsonResponse;
import com.openplan.server.web.error.exception.InternalRestfulException;

public class JsonResponsePaser {
	
	private static final Logger LOG = LoggerFactory.getLogger(JsonResponsePaser.class);
	
	public static <T> T safeGet(JsonResponse<T> response) {
		if (response == null) {
			throw new InternalRestfulException(GlobalError.INTERNAL_ERROR, "RESPONSE NULL");
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("JsonResponse ErrorCode:{}", response.getCode());
		}
		if (response.getCode() != GlobalError.OK) {
			throw new InternalRestfulException(response.getCode(), response.getMessage());
		}
		T data = response.getData();
		if (data == null) {
			throw new InternalRestfulException(GlobalError.INTERNAL_ERROR, "RESPONSE BODY NULL");
		}
		return data;
	}

}
