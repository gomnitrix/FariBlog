package com.gomnitrix.commons.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Util {
    private static final Base64.Encoder encoder = Base64.getEncoder();
    private static final Base64.Decoder decoder = Base64.getDecoder();

    public static String base64Encode(String plainText){
        byte[] textBytes = plainText.getBytes(StandardCharsets.UTF_8);
        return encoder.encodeToString(textBytes);
    }

    public static String base64Decode(String cipherText){
        return new String(decoder.decode(cipherText));
    }
}
