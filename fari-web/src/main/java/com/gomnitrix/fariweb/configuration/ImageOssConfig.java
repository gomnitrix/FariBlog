package com.gomnitrix.fariweb.configuration;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "qiniu")
public class ImageOssConfig {

    private String accessKey;

    private String secretKey;

    private String bucket;

    private String zone;

    private String domain;

    private static long expireSeconds = 3600;

    public static final String callBackUrl = "http://81.71.139.43:6002/fariWeb/oss/qiniu/callbackPoint";
    public static final String contentType = "application/json";

    @Bean
    public Auth getAuth() {
        return Auth.create(accessKey, secretKey);
    }

    public static StringMap getPolicy() {
        StringMap policy = new StringMap();
        policy.putNotEmpty("mimeLimit", "image/jpeg;image/png;image/gif;");
        policy.putNotEmpty("callbackUrl", callBackUrl);
        policy.putNotEmpty("callbackBodyType", contentType);
        policy.put("callbackBody", "{\"imgName\":\"$(fname)\"," +
                                            "\"imgUrl\":\"$(key)\","+
                                            "\"extensionName\":\"$(ext)\"," +
                                            "\"fileSize\":\"$(fsize)\","+
                                            "\"imgType\":\"$(x:ftype)\"," +
                                            "\"userUid\":\"$(x:userid)\"," +
                                            "\"blogUid\":\"$(x:blogid)\"}");
        return policy;
    }

    public static long getExpireSeconds() {
        return expireSeconds;
    }
}
