package com.openplan.server.exception;

public class SpringException extends RuntimeException {

    private static final long serialVersionUID = -5865385251189750860L;

    public SpringException() {
    }

    public SpringException(String message) {
        super(message);
    }

    public SpringException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpringException(Throwable cause) {
        super(cause);
    }

    protected SpringException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
