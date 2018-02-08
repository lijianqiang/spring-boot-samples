package com.openplan.server.http.response;

public class JsonResponse {

	public static final int OK = 0;

    public static final String SUCCESS_MSG = "success";

    private int code = OK;

    private String message = SUCCESS_MSG;

    private Object data;
    
    public JsonResponse() {
    	this(null);
    }
    
    public JsonResponse(Object data) {
    	this.data = data;
    	this.code = OK;
    	this.message = SUCCESS_MSG;
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
    
    
}
