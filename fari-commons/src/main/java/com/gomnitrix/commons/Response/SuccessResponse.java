package com.gomnitrix.commons.Response;

public class SuccessResponse extends Response {
    private SuccessResponse(Builder builder) {
        super(builder);
        super.setSuccess(true);
        super.setCode(0);
    }

    public static class Builder extends Response.Builder<SuccessResponse.Builder> {
        @Override
        public SuccessResponse build() {
            return new SuccessResponse(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
