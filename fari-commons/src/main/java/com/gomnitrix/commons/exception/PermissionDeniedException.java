package com.gomnitrix.commons.exception;

public class PermissionDeniedException extends BaseException{
    public PermissionDeniedException() {
        super(ErrorCode.PERMISSION_DENIED);
    }

    public PermissionDeniedException(String message) {
        super(ErrorCode.PERMISSION_DENIED, message);
    }
}
