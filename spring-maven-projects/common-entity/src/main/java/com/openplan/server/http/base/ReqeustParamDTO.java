package com.openplan.server.http.base;

import java.util.Map;

public interface ReqeustParamDTO extends HttpDTO {

    Map<String, Object> toParamMap();
}
