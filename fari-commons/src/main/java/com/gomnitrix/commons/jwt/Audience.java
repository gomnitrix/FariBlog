package com.gomnitrix.commons.jwt;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "audience")
public class Audience {
    @Autowired
    private String clientId;

    @Autowired
    private String base64Secret;

    @Autowired
    private String issuer;

    @Autowired
    private int expiresSecond;

}
