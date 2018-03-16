package com.openplan.server.web.restful.stragety;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import com.openplan.server.protocol.exception.error.GlobalError;
import com.openplan.server.protocol.result.JsonResponse;

public final class ReturnNormalResponseStragety implements ResponseStragety {
	
	private static final Logger LOG = LoggerFactory.getLogger(ReturnNormalResponseStragety.class);

	private Object body;

	private ServerHttpResponse response;

	private boolean enableDebugRequest;

	public ReturnNormalResponseStragety(Object body, ServerHttpResponse response, boolean enableDebugRequest) {
		this.response = response;
		this.body = body;
		this.enableDebugRequest = enableDebugRequest;
	}

	@Override
	public JsonResponse<?> process(ServerHttpRequest request) {
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		jsonResponse.setCode(GlobalError.OK);
		jsonResponse.setMessage(GlobalError.SUCCESS);
		jsonResponse.setData(body);
		
		if (LOG.isInfoEnabled()) {
			LOG.info("ErrorCode:{}", jsonResponse.getCode());
		}
		return jsonResponse;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
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
