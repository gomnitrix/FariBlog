package com.gomnitrix.fariweb.configuration;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageOssConfig {
    @Value("${qiniu.accessKey}")
    private static String accessKey;

    @Value("${qiniu.secretKey")
    private static String secretKey;

    @Value("${qiniu.bucket")
    protected static String bucket;

    @Value("${qiniu.zone}")
    protected static String zone;

    @Bean
    Auth getAuth(){
        return Auth.create(accessKey, secretKey);
    }

    public static String getBucket() {
        return bucket;
    }
}
