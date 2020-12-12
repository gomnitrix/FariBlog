package com.gomnitrix.farigateway.configuration;

import com.gomnitrix.commons.configuration.GatewayConstConfig;
import com.gomnitrix.commons.configuration.GeneralConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 资源服务器配置
 * Created by macro on 2020/6/19.
 */
@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfig {
    private final String jwkSetUri;

    public GatewaySecurityConfig(@Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}") final String jwkSetUri) {
        this.jwkSetUri = jwkSetUri;
    }

    //    private final AuthorizationManager authorizationManager;
//    private final RestfulAccessDeniedHandler restfulAccessDeniedHandler;
//    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http.oauth2ResourceServer().jwt()
//                .jwtDecoder(jwtDecoder());
        http.authorizeExchange()
                //允许所有访问认证服务器的请求
                .pathMatchers(GeneralConfig.AUTH_SHORTR_PATH + "/**").permitAll()
                //允许访问登录接口的请求（不是认证服务器的登录接口，而是网关的登录接口）
                .pathMatchers(GeneralConfig.FARI_LOGIN_URI).permitAll()
                //允许访问网关中的code接口，用于认证服务器向网管传递认证码，可能会废弃
                .pathMatchers(GeneralConfig.GATEWAY_REDIRECT_URI).permitAll()
                .and().csrf().disable();
        return http.build();
    }

    @Bean
    ReactiveJwtDecoder jwtDecoder() {
        return NimbusReactiveJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(GatewayConstConfig.AUTHORITY_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(GatewayConstConfig.AUTHORITY_CLAIM_NAME);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
