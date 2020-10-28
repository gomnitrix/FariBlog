package com.gomnitrix.fariauth.configuration;

import com.gomnitrix.commons.utils.RsaKeyPairUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class JwtTokenConfig {
    @Bean
    public TokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        //TODO secret key
        accessTokenConverter.setKeyPair(RsaKeyPairUtil.getKeyPair());
        return accessTokenConverter;
    }

//    public static class JwtTokenEnhancer implements TokenEnhancer{
//        @Override
//        public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
//            Map<String, Object> extraInfo = new HashMap<>();
//        }
//    }
}
