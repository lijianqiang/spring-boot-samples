package com.openplan.server.exception;

public enum ErrorCode {
    // 缺少参数
    REQUIRE_ARGUMENT(1001, "缺少参数"),
    // 无效参数(格式不对,长度过长或过短等)
    INVALID_ARGUMENT(1002, "无效参数(格式不对,长度过长或过短等)"),
    // 信息不存在
    DATA_NOT_FOUND(2000, "信息不存在"),
    // 返回空值
    METHOD_RETURN_NULL(3000, "返回空值"),
    
    // 未知错误
    UNKNOWN_ERROR(1000, "未知错误"),
	
	OK(0, "OK");
	
	private final int code;

	private final String message;
	
	ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	public static ErrorCode valueOf(int code) {
		for (ErrorCode bean : values()) {
			if (bean.code == code) {
				return bean;
			}
		}
		return UNKNOWN_ERROR;
	}

}
