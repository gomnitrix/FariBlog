package com.gomnitrix.commons.configuration;

public abstract class GatewayConstConfig {
    public static final String HTTP_PREFIX = "http://localhost:8501";
    public static final String HTTPS_PREFIX = "https://localhost:8501";

    public static final String FARI_BLOG = "/fariblog";
    public static final String LOGIN_PATH = "/login";
    public static final String CODE_PATH = "/code";

    public static final String CLIENT_ID = "fari-gateway";
    public static final String CLIENT_SECRET = "fari-gateway-20201030";
    public static final String REDIRECT_URI = HTTP_PREFIX+"/fariblog/login";

    public static final String AUTH_SERVER = HTTP_PREFIX+"/fari-auth";
}
