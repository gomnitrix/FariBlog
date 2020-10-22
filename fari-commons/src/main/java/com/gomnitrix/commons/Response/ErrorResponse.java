package com.gomnitrix.commons.Response;

public class ErrorResponse extends Response{
    private ErrorResponse(Builder builder, Exception e){
        super(builder);
        super.setSuccess(false);
        super.setMessage(e.getMessage());
    }
    public static class Builder extends Response.Builder<Builder>{
        private final Exception e;
        public Builder(Exception e){
            this.e = e;
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
