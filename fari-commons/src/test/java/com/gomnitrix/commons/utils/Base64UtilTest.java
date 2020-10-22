package com.gomnitrix.commons.utils;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class Base64UtilTest {
    private static final Base64.Encoder encoder = Base64.getEncoder();

    @Test
    void base64Encode() {
        Assert.assertEquals(encoder.encodeToString("test".getBytes(StandardCharsets.UTF_8)), Base64Util.base64Encode("test"));

    }

    @Test
    void base64Decode() {
        String input = "test";
        String cipher = Base64Util.base64Encode(input);
        Assert.assertEquals(input, Base64Util.base64Decode(cipher));
    }
}