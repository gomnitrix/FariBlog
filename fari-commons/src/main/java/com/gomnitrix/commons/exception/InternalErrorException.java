package com.gomnitrix.commons.exception;

public class InternalErrorException extends BaseException{
    public InternalErrorException(){
        super(ErrorCode.INTERNAL_ERROR);
    }
}
