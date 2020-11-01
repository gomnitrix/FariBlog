//package com.gomnitrix.farigateway.configuration;
//
//import com.gomnitrix.farigateway.service.AuthRemoteServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
//@Configuration
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class GatewayResourceServerConfig extends ResourceServerConfigurerAdapter {
//    @Autowired
//    AuthRemoteServiceImpl authRemoteService;
//
//    @Value("${security.oauth2.client.client-id}")
//    private String clientId;
//    @Value("${security.oauth2.client.client-secret}")
//    private String secret;
//    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}") String jwkSetUri;
//
//    @Autowired
//    private TokenStore jwtTokenStore;
//
//    @Bean
//    public TokenStore jwtTokenStore() {
//        return new JwtTokenStore(jwtAccessTokenConverter());
//    }
//
//    @Bean
//    public String authRsaPublicKey() {
//        return authRemoteService.getPublicKey();
//    }
//
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter(){
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setVerifierKey(authRsaPublicKey());
//        return converter;
//    }
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.tokenStore(jwtTokenStore);
//    }
//}
