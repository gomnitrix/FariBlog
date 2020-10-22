package com.gomnitrix.commons.exception;

public class InvalidJwtException extends BaseException{
    public InvalidJwtException(){
        super(ErrorCode.JWT_VERIFICATION_FAILED);
    }
}
