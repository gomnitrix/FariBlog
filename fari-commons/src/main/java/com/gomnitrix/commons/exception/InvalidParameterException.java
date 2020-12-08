package com.gomnitrix.commons.exception;

public class InvalidParameterException extends BaseException{
    public InvalidParameterException() {
        super(ErrorCode.INVALID_PARAMETER);
    }
}
