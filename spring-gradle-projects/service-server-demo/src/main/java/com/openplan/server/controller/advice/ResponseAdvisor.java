package com.openplan.server.controller.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.openplan.server.http.response.JsonResponse;

@ControllerAdvice
public class ResponseAdvisor implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		HttpHeaders headers = request.getHeaders();
		String client = headers.getFirst("x-inner-client");
		if ("inner-raw-object".equals(client)) {
			return body;
		}
//		String requestPath = request.getURI().getPath();
//		if (!requestPath.startsWith("/ug")) {
//			return body;
//		}
//
		if (body instanceof JsonResponse) {
			response.setStatusCode(HttpStatus.BAD_REQUEST);
			return body;
		}
		return new JsonResponse(body);
	}

}
