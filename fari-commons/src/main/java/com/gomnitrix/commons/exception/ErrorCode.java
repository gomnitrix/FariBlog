package com.gomnitrix.commons.exception;

/**
 * manage all Exception code
 */
public enum ErrorCode {
    JWT_VERIFICATION_FAILED(1001, "Invalid Or Expired Token."),
    INTERNAL_ERROR(5001, "Some Internal Errors happened");

    private final Integer code;
    private final String message;

    ErrorCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
