package com.openplan.server.web.restful.stragety;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import com.openplan.server.protocol.exception.error.GlobalError;
import com.openplan.server.protocol.result.JsonResponse;
import com.openplan.server.web.restful.helper.ResponseStragetyHelper;

public final class ReturnNullResponseStragety implements ResponseStragety {
	
	private static final Logger LOG = LoggerFactory.getLogger(ReturnNullResponseStragety.class);

	private ServerHttpResponse response;

	private boolean enableDebugRequest;

	public ReturnNullResponseStragety() {
		this(null, false);
	}

	public ReturnNullResponseStragety(ServerHttpResponse response, boolean enableDebugRequest) {
		this.response = response;
		this.enableDebugRequest = enableDebugRequest;
	}

	@Override
	public JsonResponse<?> process(ServerHttpRequest request) {
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		jsonResponse.setCode(GlobalError.UNDEFINED_ERROR);
		String nullTIp = "METHOD_RETURN_NULL";
		HttpHeaders headers = request.getHeaders();
		if (ResponseStragetyHelper.isDebugRequest(headers, enableDebugRequest)) {
			jsonResponse.setMessage(buildDebugNullMessage(request, nullTIp));
		} else {
			jsonResponse.setMessage(nullTIp);
		}
		HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
		if (response != null) {
			response.setStatusCode(httpStatus);
		}
		if (LOG.isInfoEnabled()) {
			LOG.info("ErrorCode:{}, HttpStatus:{}", jsonResponse.getCode(), httpStatus.value());
		}
		return jsonResponse;
	}

	private String buildDebugNullMessage(ServerHttpRequest request, String nullTip) {
		StringBuilder sb = new StringBuilder();
		sb.append(request.getURI().getPath()).append(":").append(nullTip);
		return sb.toString();
	}

	public ServerHttpResponse getResponse() {
		return response;
	}

	public void setResponse(ServerHttpResponse response) {
		this.response = response;
	}

}
