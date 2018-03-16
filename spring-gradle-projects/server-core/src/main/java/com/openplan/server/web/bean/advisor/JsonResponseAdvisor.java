package com.openplan.server.web.bean.advisor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.openplan.server.core.CoreWebProperties;
import com.openplan.server.web.restful.factory.ResponseStragetyFactory;
import com.openplan.server.web.restful.stragety.ResponseStragety;

@ControllerAdvice
@ConditionalOnProperty(prefix = "server.core.web", name = "response.advisor.enable", matchIfMissing = true)
public final class JsonResponseAdvisor implements ResponseBodyAdvice<Object> {
	
	// server.core.web.response.advisor.enable=true
	
	@Autowired
	private CoreWebProperties coreWebProperties;
	
	private static final Logger LOG = LoggerFactory.getLogger(JsonResponseAdvisor.class);
	
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return coreWebProperties.isResponseAdvisorEnable();
	}

	@Override
	public final Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("MediaType: {}", selectedContentType.toString());
		}
		if (MediaType.TEXT_HTML.equals(selectedContentType)) {
			return body;
		}
		ResponseStragety responseStragety = ResponseStragetyFactory.build(body, response, returnType, isEnableDebugRequest());
		
		return responseStragety.process(request);
	}

	// server.core.web.restful.debug.enable
	protected boolean isEnableDebugRequest() {
		return coreWebProperties.isRestfulDebugEnable();
	};
}
