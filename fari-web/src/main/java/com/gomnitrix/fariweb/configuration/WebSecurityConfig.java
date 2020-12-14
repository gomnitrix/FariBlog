package com.gomnitrix.fariweb.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // 由于使用的是JWT，这里不需要csrf
            .cors()//允许跨域
            .and()
            .csrf().disable()

            // 基于token，不需要session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

            .authorizeRequests()

            // 允许对于网站静态资源的无授权访问
            .antMatchers(
                    "/swagger-ui.html",
                    "/swagger-ui/*",
                    "/swagger-resources/**",
                    "/v2/api-docs",
                    "/v3/api-docs",
                    "/webjars/**",
                    "/actuator/**",
                    "/druid/**"
            ).permitAll()
            // 对于获取token的rest api要允许匿名访问
            .antMatchers("/index/**",
                    "/resource/**",
                    "/about/**",
                    "/labs/test/**",
                    "/search/**",
                    "/content/**",
                    "/sort/**",
                    "/classify/**",
                    "/tag/**",
                    "/subject/**",
                    "/login/**",
                    "/oauth/**",
                    "/web/comment/**",
                    "/freemarker/**",
                    "/sysDictData/**",
                    "/elasticSearchBlog/**"
            ).permitAll()
            // 除上面外的所有请求全部需要鉴权认证
            .anyRequest().authenticated();


        // 禁用缓存
        http.headers().cacheControl();
    }
}
