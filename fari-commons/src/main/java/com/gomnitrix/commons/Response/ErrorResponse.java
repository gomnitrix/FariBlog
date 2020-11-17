package com.gomnitrix.commons.Response;

import com.gomnitrix.commons.exception.BaseException;
import org.springframework.stereotype.Component;

public class ErrorResponse extends Response {
    private ErrorResponse(Builder builder, BaseException e) {
        super(builder);
        super.setSuccess(false);
        super.setMessage(e.getError().getMessage());
    }

    @Component
    public static class Builder extends Response.Builder<Builder> {
        private final BaseException e;

        public Builder(BaseException e) {
            this.e = e;
            super.setCode(e.getError().getCode());
        }

        public Builder setMessage(String message){
            this.e.getError().setMessage(message);
            return this;
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
