package com.openplan.server.http.response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.openplan.server.http.base.ResponseBodyDTO;

public class ResponseBuilder {
    
    private static final String GSON_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
    
    private static final Gson gson = new GsonBuilder().serializeNulls().setDateFormat(GSON_DATE_FORMAT).create();
    
    public static String build(int code, String message, ResponseBodyDTO data) {
        JsonResponse<ResponseBodyDTO> jsonResponse = new JsonResponse<ResponseBodyDTO>();
        jsonResponse.setCode(code);
        jsonResponse.setMessage(message);
        jsonResponse.setData(data);
        return gson.toJson(jsonResponse);
    }
    
    public static String success(ResponseBodyDTO data) {
        return build(JsonResponse.OK, JsonResponse.SUCCESS_MSG, data);
    }
    
    public static String success() {
        return build(JsonResponse.OK, JsonResponse.SUCCESS_MSG, new EmptyResponseBody());
    }
    
    public static String fail(int code, String message) {
        return build(code, message, new EmptyResponseBody());
    }
    
    public static Gson getGson() {
        return gson;
    }
}
