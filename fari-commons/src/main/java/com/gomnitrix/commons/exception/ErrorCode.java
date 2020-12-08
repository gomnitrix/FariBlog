package com.gomnitrix.commons.exception;

/**
 * manage all Exception code
 * AUTH FAILED -> 1XXX
 * RESOURCE NOT FOUND -> 2XXX
 * OTHER ERROR -> 5XXX
 */
public enum ErrorCode {
    JWT_VERIFICATION_FAILED(1001, "Invalid Or Expired Token."),
    USER_NOT_FOUND(2001, "No Such User Exist"),
    INTERNAL_ERROR(5001, "Some Internal Errors Happened"),
    RSA_KEY_EXCEPTION(5002, "Some Error Occurs When Use Rsa Key Pairs"),
    AUTHENTICATION_FAILED(1002, "Username Or Password Wrong."),
    INVALID_PARAMETER(5003, "Some Error Occurs While Parsing Parameter.");


    private final Integer code;
    private String message;

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

    public void setMessage(String message){
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
