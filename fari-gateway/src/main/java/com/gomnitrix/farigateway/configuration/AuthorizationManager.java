package com.gomnitrix.farigateway.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    @Autowired
    IgnoreUrlsConfig ignoreUrlsConfig;

    JwtDecoder jwtDecoder;

    private Set<String> permitUrls;
    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    public AuthorizationManager(@Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}") final String jwkSetUri) {
        jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
    }

    @PostConstruct
    public void init() {
        this.permitUrls = new HashSet<>(ignoreUrlsConfig.getUrls());
    }


    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerWebExchange exchange = authorizationContext.getExchange();
        //请求资源
        String requestPath = exchange.getRequest().getURI().getPath();
        // 是否直接放行，对于预检请求也需直接放行
        if (permitAll(requestPath) || exchange.getRequest().getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(true));
        }
        String raw_token = exchange.getRequest().getHeaders().getFirst("Authorization");
        try {
            Jwt jwt = jwtDecoder.decode(raw_token);
            if (jwt == null) {
                return Mono.just(new AuthorizationDecision(false));
            }

            Map<String, Object> claims = jwt.getClaims();
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .headers(httpHeaders -> {
                        // 将jwt中的信息放入http头部中
                        httpHeaders.add("username", claims.get("user_name").toString());
                        httpHeaders.add("userId", claims.get("userId").toString());
                        //TODO 后续需要添加别的jwt里的项可以在这里添加，目前还有两个可能有用的字段：role, client_id
                    }).build();

            exchange.mutate().request(request).build();
            return Mono.just(new AuthorizationDecision(true));
        } catch (Exception e) {
            log.error("Jwt decode failed in AuthorizationManager.java: "+e.getMessage());
        }
        return Mono.just(new AuthorizationDecision(false));
    }

    /**
     * 校验是否属于白名单路径
     *
     * @param requestPath 请求路径
     * @return
     */
    private boolean permitAll(String requestPath) {
        return permitUrls.stream()
                .anyMatch(r -> antPathMatcher.match(r, requestPath));
    }

    //权限校验
    private boolean checkAuthorities(ServerWebExchange exchange, Authentication auth, String requestPath) {
        if (auth instanceof OAuth2Authentication) {
            OAuth2Authentication authentication = (OAuth2Authentication) auth;
            String clientId = authentication.getOAuth2Request().getClientId();
            log.info("clientId is {}", clientId);
        }

        Object principal = auth.getPrincipal();
        log.info("用户信息:{}", principal.toString());
        return true;
    }
}
