package com.gomnitrix.fariauth.configuration;

import com.gomnitrix.commons.configuration.AuthServerConstConfig;
import com.gomnitrix.commons.entity.User;
import com.gomnitrix.commons.utils.RsaKeyPairUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JwtTokenConfig {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Qualifier("jdbcClientDetailsService")
    @Autowired
    private ClientDetailsService jdbcService;

    @Bean
    public ClientDetailsService jdbcClientDetailsService() throws Exception {
        JdbcClientDetailsServiceBuilder builder = new JdbcClientDetailsServiceBuilder();
        builder.dataSource(dataSource);
        builder.passwordEncoder(passwordEncoder);
        return builder.build();
    }

    @Bean
    public OAuth2RequestFactory oAuth2RequestFactory() {
        return new DefaultOAuth2RequestFactory(jdbcService);
    }


    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setKeyPair(rsaKeyPair());
        return accessTokenConverter;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new JwtTokenEnhancer();
    }

    @Bean
    public KeyPair rsaKeyPair() {
        return RsaKeyPairUtil.getKeyPair();
    }

    @Component
    public static class JwtTokenEnhancer implements TokenEnhancer {
        @Override
        public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
            Object principal = oAuth2Authentication.getPrincipal();
            if (principal instanceof User) {
                User user = (User) principal;
                Map<String, Object> extraInfo = new HashMap<>();
                extraInfo.put(AuthServerConstConfig.USER_ID, user.getUid());
                extraInfo.put(AuthServerConstConfig.ROLE, user.getUserRole());
                ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(extraInfo);
            }
            return oAuth2AccessToken;
        }
    }
}
