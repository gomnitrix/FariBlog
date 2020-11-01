package com.gomnitrix.commons.configuration;

public abstract class GatewayConstConfig {
    public static final String HTTP_PREFIX = "http://localhost:6002";
    public static final String HTTPS_PREFIX = "https://localhost:6002";


    public static final String REDIRECT_URI = GeneralConfig.GATEWAY_SHORTR_PATH + GeneralConfig.CODE_PATH;
    public static final String REDIRECT_URL = HTTP_PREFIX+REDIRECT_URI;
    public static final String LOGIN_URI = GeneralConfig.AUTH_SHORTR_PATH + GeneralConfig.LOGIN_PATH;

    public static final String AUTHORITY_PREFIX = "ROLE_";

    public static final String AUTHORITY_CLAIM_NAME = "authorities";
}
