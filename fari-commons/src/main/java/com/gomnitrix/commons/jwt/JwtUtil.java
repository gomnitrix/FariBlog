package com.gomnitrix.commons.jwt;

import com.gomnitrix.commons.utils.Base64Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtil {

    private static Key generateKey(String secretKey) {
        byte[] encodedKey = DatatypeConverter.parseBase64Binary(secretKey);
        return new SecretKeySpec(encodedKey, SignatureAlgorithm.HS256.getJcaName());
    }

    public static String createJWT(String userId, String userName, String role, Audience audience) {
        // 生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        Key key = generateKey(audience.getBase64Secret());

        String encryUserId = Base64Util.base64Encode(userId); //userID加密
        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", encryUserId);
        claims.put("role", role);

        JwtBuilder builder = Jwts.builder()
                // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                .setIssuedAt(now)
                .setIssuer(audience.getIssuer())
                .setSubject(userName)
                .signWith(key);
        // 设置过期时间
        int ttlMillis = audience.getExpiresSecond();
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
    private static Claims parseJWT(String jwt, String base64Security) {
        return Jwts.parserBuilder()
                            .setSigningKey(generateKey(base64Security))
                            .build()
                            .parseClaimsJws(jwt)
                            .getBody();
    }

    /**
     * get UserName from jwt
     * @param jwt
     * @param base64Security
     * @return
     */
    public static String getUserName(String jwt, String base64Security){
        return parseJWT(jwt, base64Security).getSubject();
    }

    /**
     * get plain userId from jwt
     * @param jwt
     * @param base64Security
     * @return
     */
    public static String getUserId(String jwt, String base64Security){
        String cipherUserId = parseJWT(jwt, base64Security).get("userId", String.class);
        return Base64Util.base64Decode(cipherUserId);
    }

    /**
     * check if token expired
     * @param jwt
     * @param base64Security
     * @return
     */
    public static Boolean isExpiration(String jwt, String base64Security) {
        return parseJWT(jwt, base64Security).getExpiration().before(new Date());
    }

    public static Boolean isExpiration(Claims claims){
        return claims.getExpiration().before(new Date());
    }

    /**
     * 检查token
     *
     * @return
     */
    public static boolean checkToken(String jwt, Audience audience) throws Exception {
        Claims claims = JwtUtil.parseJWT(jwt, audience.getBase64Secret());
        return isExpiration(claims);
    }
}