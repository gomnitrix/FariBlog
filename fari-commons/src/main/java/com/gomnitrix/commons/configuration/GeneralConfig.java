package com.gomnitrix.commons.configuration;

import org.bouncycastle.jcajce.provider.asymmetric.RSA;

public abstract class GeneralConfig {
    // resource path
    public static final String RAW_RESOURCE_PATH = GeneralConfig.class.getResource("").toString();
    public static final String RESOURCE_PATH = RAW_RESOURCE_PATH.substring(RAW_RESOURCE_PATH.indexOf("/"));

    // ras key pair
    public static final String RSA = "RSA";
    public static final int KEY_SIZE = 1024;
    public static final String PUB_FILE = RESOURCE_PATH+"public.pem";
    public static final String PRI_FILE = RESOURCE_PATH+"private.pem";
}
