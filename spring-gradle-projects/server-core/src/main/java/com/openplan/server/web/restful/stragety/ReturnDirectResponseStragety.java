package com.openplan.server.web.restful.stragety;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import com.openplan.server.protocol.exception.error.GlobalError;
import com.openplan.server.protocol.result.JsonResponse;
import com.openplan.server.web.restful.helper.ResponseStragetyHelper;

public final class ReturnDirectResponseStragety implements ResponseStragety {
	
	private static final Logger LOG = LoggerFactory.getLogger(ReturnDirectResponseStragety.class);

	private JsonResponse<?> jsonResponse;

	private ServerHttpResponse response;
	
	private boolean enableDebugRequest;

	public ReturnDirectResponseStragety(JsonResponse<?> jsonResponse, ServerHttpResponse response, boolean enableDebugRequest) {
		this.response = response;
		this.jsonResponse = jsonResponse;
		this.enableDebugRequest = enableDebugRequest;
	}

	@Override
	public JsonResponse<?> process(ServerHttpRequest request) {
		boolean isError = jsonResponse.getCode() != GlobalError.OK;
		HttpStatus httpStatus = isError ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
		if (isError && ResponseStragetyHelper.isForceHttpStatusOk(request.getHeaders())) {
			httpStatus = HttpStatus.OK;
		} 
		
		if (response != null) {
			response.setStatusCode(httpStatus);
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("ErrorCode:{}, HttpStatus:{}", jsonResponse.getCode(), httpStatus.value());
		}
		return jsonResponse;
	}

	public JsonResponse<?> getJsonResponse() {
		return jsonResponse;
	}

	public void setJsonResponse(JsonResponse<?> jsonResponse) {
		this.jsonResponse = jsonResponse;
	}

	public ServerHttpResponse getResponse() {
		return response;
	}

	public void setResponse(ServerHttpResponse response) {
		this.response = response;
	}

	public boolean isEnableDebugRequest() {
		return enableDebugRequest;
	}

	public void setEnableDebugRequest(boolean enableDebugRequest) {
		this.enableDebugRequest = enableDebugRequest;
	}

}
