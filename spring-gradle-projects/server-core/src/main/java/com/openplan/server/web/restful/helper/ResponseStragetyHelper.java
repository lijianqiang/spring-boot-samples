package com.openplan.server.web.restful.helper;

import org.springframework.http.HttpHeaders;

import com.openplan.server.core.http.constant.DebugHeader;
import com.openplan.server.core.http.constant.ForceStatusHeader;

public class ResponseStragetyHelper {
	
	public static final boolean isDebugRequest(HttpHeaders headers, boolean isEnableDebugRequest) {
		if (isEnableDebugRequest == false) {
			return false;
		}
		String value = headers.getFirst(DebugHeader.DEBUG_HTTP_HEADER_KEY);
		return DebugHeader.DEBUG_HTTP_HEADER_VALUE.equals(value);
	}

	public static final boolean isForceHttpStatusOk(HttpHeaders headers) {
		String val = headers.getFirst(ForceStatusHeader.FORCE_STATUS_OK_KEY);
		return ForceStatusHeader.FORCE_STATUS_OK_VALUE.equals(val);
	}

}
