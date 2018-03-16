package com.openplan.server.web.error.message;

public final class DebugErrorMessage implements ErrorMessage {

	private String error;
	
	private String detail;
	
	private String uri;
	
	private String stackTrace;
	
	public DebugErrorMessage() {
	}
	
	@Override
	public final String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("detail=").append(detail).append(", ");
		sb.append("error=").append(error).append(", ");
		sb.append("uri=").append(uri).append(", ");
		sb.append("stack_trace=").append(stackTrace);
		return sb.toString();
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

}
