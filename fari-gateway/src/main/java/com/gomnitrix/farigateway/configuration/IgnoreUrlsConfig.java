package com.gomnitrix.farigateway.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "fari-gateway.secure.white-list")
public class IgnoreUrlsConfig {
    private List<String> urls;
}
