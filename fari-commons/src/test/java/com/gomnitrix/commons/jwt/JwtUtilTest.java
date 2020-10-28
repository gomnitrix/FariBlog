package com.gomnitrix.commons.jwt;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class JwtUtilTest {
    private static final String userId = "testID";
    private static final String userName = "testName";
    private static final String role = "testRole";
    private static final String audience = "testAudience";
    private static final String testJwt = JwtUtil.createJWT(userId, userName, role, audience);

//    @Test
//    void createJWT() {
//    }

//    @Test
//    void getUserName() {
//        Assert.assertEquals(userName, JwtUtil.getUserName(testJwt));
//    }
//
//    @Test
//    void getUserId() {
//        Assert.assertEquals(userId, JwtUtil.getUserId(testJwt));
//    }

    @Test
    void isExpiration() {
        String testJwt2 = JwtUtil.createJWT(userId, userName, role, audience);
        Assert.assertEquals(Boolean.FALSE, JwtUtil.isExpiration(testJwt2));
    }

    @Test
    void checkToken() {
        String testJwt2 = JwtUtil.createJWT(userId, userName, role, audience);
        Assert.assertEquals(Boolean.FALSE, JwtUtil.isExpiration(testJwt2));
    }
}