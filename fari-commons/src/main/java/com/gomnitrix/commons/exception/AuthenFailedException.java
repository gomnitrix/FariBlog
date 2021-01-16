package com.gomnitrix.commons.exception;

public class AuthenFailedException extends BaseException {
    public AuthenFailedException() {
        super(ErrorCode.AUTHENTICATION_FAILED);
    }

    public AuthenFailedException(String message) {
        super(ErrorCode.AUTHENTICATION_FAILED, message);
    }
}
