package com.gomnitrix.commons.Response;

import com.gomnitrix.commons.exception.BaseException;

public class ErrorResponse extends Response {
    private ErrorResponse(Builder builder, BaseException e) {
        super(builder);
        super.setSuccess(false);
        super.setMessage(e.getMessage());
    }

    public static class Builder extends Response.Builder<Builder> {
        private final BaseException e;

        public Builder(BaseException e) {
            this.e = e;
            this.setCode(e.getError().getCode());
        }

        @Override
        public ErrorResponse build() {
            return new ErrorResponse(this, e);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

}
