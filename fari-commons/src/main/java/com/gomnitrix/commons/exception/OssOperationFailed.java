package com.gomnitrix.commons.exception;

public class OssOperationFailed extends BaseException{
    public OssOperationFailed() {
        super(ErrorCode.OSS_OPERATION_FAILED);
    }

    public OssOperationFailed(String message){
        super(ErrorCode.OSS_OPERATION_FAILED, message);
    }
}
