package com.openplan.server.web.restful.stragety;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import com.openplan.server.protocol.result.JsonResponse;
import com.openplan.server.web.error.exception.entity.ExceptionEntity;
import com.openplan.server.web.error.message.DebugErrorMessage;
import com.openplan.server.web.error.message.NormalErrorMessage;
import com.openplan.server.web.restful.helper.ResponseStragetyHelper;

public final class ReturnErrorResponseStragety implements ResponseStragety {
	
	private static final Logger LOG = LoggerFactory.getLogger(ReturnErrorResponseStragety.class);

	private ExceptionEntity error;

	private ServerHttpResponse response;

	private boolean enableDebugRequest;

	public ReturnErrorResponseStragety(ExceptionEntity error, ServerHttpResponse response, boolean enableDebugRequest) {
		this.response = response;
		this.error = error;
		this.enableDebugRequest = enableDebugRequest;
	}

	@Override
	public JsonResponse<?> process(ServerHttpRequest request) {
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		HttpStatus httpStatus = HttpStatus.valueOf(error.getStatus());
		String showTip = "";
		HttpHeaders headers = request.getHeaders();
		if (ResponseStragetyHelper.isDebugRequest(headers, enableDebugRequest)) {
			DebugErrorMessage debugMessage = buildDebugErrorMessage(error, request);
			showTip = debugMessage.toString();
		} else {
			NormalErrorMessage normalMessage = buildNormalErrorMessage(error);
			showTip = normalMessage.toString();
		}
		jsonResponse.setCode(error.getCode());
		jsonResponse.setMessage(filterShowTip(showTip));
		if (ResponseStragetyHelper.isForceHttpStatusOk(headers)) {
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

	private DebugErrorMessage buildDebugErrorMessage(ExceptionEntity error, ServerHttpRequest request) {
		DebugErrorMessage message = new DebugErrorMessage();
		Exception ex = error.getException();
		message.setUri(formatUri(request.getURI()));
		// message.setClientIp(request.getRemoteAddress().getAddress().getHostAddress());
		if (ex != null) {
			message.setError(ex.getClass().getSimpleName());
			message.setDetail(ex.getMessage());
			message.setStackTrace(formatTrace(ex)); // TODO
		}
		return message;
	}

	private NormalErrorMessage buildNormalErrorMessage(ExceptionEntity error) {
		NormalErrorMessage message = new NormalErrorMessage();
		Exception ex = error.getException();
		if (ex != null) {
			message.setContent(ex.getMessage() != null ? ex.getMessage() : ex.getClass().getSimpleName());
		}
		return message;
	}

	private String filterShowTip(String showTip) {
		if (showTip == null || showTip.length() < 1) {
			return "unknown";
		}
		if (showTip.length() > 512) {
			return showTip.substring(0, 512);
		}
		return showTip;
	}
	
	private String formatTrace(Exception ex) {
		if (ex == null || ex.getStackTrace() == null || ex.getStackTrace().length < 1) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(ex.getStackTrace()[0].toString()); // first line
		return sb.toString();
	}
	
	private String formatUri(URI uri) {
		StringBuilder sb = new StringBuilder();
		sb.append(uri.getPort()).append(uri.getPath());
		if (uri.getQuery() != null) {
			sb.append("?");
			sb.append(uri.getQuery());
		}
		return sb.toString();
	}

	public ExceptionEntity getError() {
		return error;
	}

	public void setError(ExceptionEntity error) {
		this.error = error;
	}

	public ServerHttpResponse getResponse() {
		return response;
	}

	public void setResponse(ServerHttpResponse response) {
		this.response = response;
	}
	

}
