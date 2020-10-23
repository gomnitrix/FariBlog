package com.gomnitrix.commons.exception;

import java.util.Map;

public class InvalidJwtException extends BaseException{
    public InvalidJwtException(){
        super(ErrorCode.JWT_VERIFICATION_FAILED);
    }
}
