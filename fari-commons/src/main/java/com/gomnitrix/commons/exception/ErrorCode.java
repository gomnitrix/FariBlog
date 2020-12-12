package com.gomnitrix.commons.exception;

import org.springframework.http.HttpStatus;

/**
 * manage all Exception code
 * AUTH FAILED -> 1XXX
 * RESOURCE NOT FOUND -> 2XXX
 * OTHER ERROR -> 5XXX
 */
public enum ErrorCode {
    JWT_VERIFICATION_FAILED(1001, HttpStatus.FORBIDDEN, "Invalid Or Expired Token."),
    AUTHENTICATION_FAILED(1002, HttpStatus.UNAUTHORIZED,"Username Or Password Wrong."),
    USER_NOT_FOUND(2001, HttpStatus.UNAUTHORIZED, "No Such User Exist"),
    INTERNAL_ERROR(5001, HttpStatus.INTERNAL_SERVER_ERROR, "Some Internal Errors Happened"),
    RSA_KEY_EXCEPTION(5002, HttpStatus.INTERNAL_SERVER_ERROR, "Some Error Occurs When Use Rsa Key Pairs"),
    INVALID_PARAMETER(5003, HttpStatus.BAD_REQUEST, "Some Error Occurs While Parsing Parameter.");


    private final Integer code;
    private final HttpStatus status;
    private final String message;

    ErrorCode(int code, HttpStatus status, String message){
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {return status;}

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
