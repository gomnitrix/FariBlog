package com.gomnitrix.commons.jwt;

import com.gomnitrix.commons.exception.InvalidJwtException;
import com.gomnitrix.commons.utils.Base64Util;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class JwtUtil {
    private static Key secretKey;
    private static String issuer;
    private static Integer expiresSecond;
    static {
        Properties properties = new Properties();
        InputStream in = JwtUtil.class.getResourceAsStream("/jwt.properties");
        try {
            properties.load(in);
            secretKey = generateKey(properties.getProperty("base64Secret").trim());
            issuer = properties.getProperty("issuer").trim();
            expiresSecond = Integer.parseInt(properties.getProperty("expiresSecond").trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Key generateKey(String secretKey) {
        byte[] encodedKey = DatatypeConverter.parseBase64Binary(secretKey);
        return new SecretKeySpec(encodedKey, SignatureAlgorithm.HS256.getJcaName());
    }

    public static String createJWT(String userId, String userName, String role, String audience) {
        // 生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        String encryUserId = Base64Util.base64Encode(userId); //userID加密
        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", encryUserId);
        claims.put("role", role);

        JwtBuilder builder = Jwts.builder()
                // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                .setIssuedAt(now)
                .setIssuer(issuer)
                .setSubject(userName)
                .setAudience(audience)
                .signWith(secretKey);
        // 设置过期时间
        int ttlMillis = expiresSecond;
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp)
                    .setNotBefore(now);
        }
        return builder.compact();
    }

    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     */
    private static Claims parseJWT(String jwt) {
        Claims claims;
        try{
            claims = Jwts.parserBuilder()
                            .setSigningKey(secretKey)
                            .build()
                            .parseClaimsJws(jwt)
                            .getBody();
        }catch (JwtException e){
            //TODO log
            throw new InvalidJwtException();
        }
        return claims;
    }

    /**
     * get UserName from jwt
     * @param jwt
     * @return
     */
    public static String getUserName(String jwt){
        return parseJWT(jwt).getSubject();
    }

    /**
     * get plain userId from jwt
     * @param jwt
     * @return
     */
    public static String getUserId(String jwt){
        String cipherUserId = parseJWT(jwt).get("uid", String.class);
        return Base64Util.base64Decode(cipherUserId);
    }

    public static String getAudience(String jwt){
        return parseJWT(jwt).getAudience();
    }

    /**
     * check if token expired
     * @param jwt
     * @return
     */
    public static Boolean isExpiration(String jwt) {
        return parseJWT(jwt).getExpiration().before(new Date());
    }

    public static Boolean isExpiration(Claims claims){
        return claims.getExpiration().before(new Date());
    }

    /**
     * 检查token
     *
     * @return
     */
    public static boolean checkToken(String jwt) {
        Claims claims = JwtUtil.parseJWT(jwt);
        return isExpiration(claims);
    }
}