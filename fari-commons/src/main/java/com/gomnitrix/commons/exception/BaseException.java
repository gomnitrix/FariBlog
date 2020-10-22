package com.gomnitrix.commons.exception;


public abstract class BaseException extends RuntimeException {
    private final ErrorCode error;
    private String message;

    public BaseException(ErrorCode error) {
        super(error.getMessage());
        this.error = error;
        this.message = error.getMessage();
    }

    public BaseException(ErrorCode error, String message) {
        super(message);
        this.error = error;
        this.message = message;
    }

    public ErrorCode getError() {
        return error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
