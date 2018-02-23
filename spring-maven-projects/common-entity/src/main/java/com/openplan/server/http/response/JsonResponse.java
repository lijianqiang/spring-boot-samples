package com.openplan.server.http.response;

import com.openplan.server.http.base.ResponseBodyDTO;
import com.openplan.server.http.base.ResponseDTO;

public class JsonResponse<T extends ResponseBodyDTO> implements ResponseDTO {

    private static final long serialVersionUID = 363522840067808676L;

    public static final int OK = 0;

    public static final String SUCCESS_MSG = "success";

    private int code;

    private String message;

    private T data;

    public JsonResponse() {
        this(OK, SUCCESS_MSG);
    }

    public JsonResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
