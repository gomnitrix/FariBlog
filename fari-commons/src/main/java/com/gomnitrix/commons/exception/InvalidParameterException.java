package com.gomnitrix.commons.exception;

public class InvalidParameterException extends BaseException{
    public InvalidParameterException() {
        super(ErrorCode.INVALID_PARAMETER);
    }

    public InvalidParameterException(String message){
        super(ErrorCode.INVALID_PARAMETER, message);
    }
}
