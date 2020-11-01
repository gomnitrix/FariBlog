package com.gomnitrix.commons.configuration;

public abstract class GeneralConfig {
    // resource path
    public static final String RAW_RESOURCE_PATH = GeneralConfig.class.getResource("").toString();
    public static final String RESOURCE_PATH = RAW_RESOURCE_PATH.substring(RAW_RESOURCE_PATH.indexOf("/"));

    // ras key pair
    public static final String RSA = "RSA";
    public static final int KEY_SIZE = 1024;
    public static final String PUB_FILE = RESOURCE_PATH+"public.pem";
    public static final String PRI_FILE = RESOURCE_PATH+"private.pem";

    //server name
    public static final String FARI_AUTH = "fari-auth";
    public static final String FARI_GATEWAY = "fari-gateway";

    //server URL
    public static final String AUTH_SERVER_URL = GatewayConstConfig.HTTP_PREFIX + "/" + FARI_AUTH;
}
