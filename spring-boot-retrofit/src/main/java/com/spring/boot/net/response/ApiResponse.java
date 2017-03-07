package com.spring.boot.net.response;

public class ApiResponse<T> {
    private int code;
    
    private String message;
    
    private T response;
    
    public ApiResponse() {
    	this.code = 0;
    	this.message = "ok";
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

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
    
}
