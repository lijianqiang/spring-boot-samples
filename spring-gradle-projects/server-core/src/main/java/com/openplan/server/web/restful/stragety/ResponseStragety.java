package com.openplan.server.web.restful.stragety;

import org.springframework.http.server.ServerHttpRequest;

import com.openplan.server.protocol.result.JsonResponse;

public interface ResponseStragety {
	
	JsonResponse<?> process(ServerHttpRequest request);
}
