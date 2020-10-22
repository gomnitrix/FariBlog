package com.gomnitrix.fariauth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;

import javax.sql.DataSource;

@Configuration
public class ClientConfig {
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

}
