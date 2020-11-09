package com.gomnitrix.fariauth.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gomnitrix.commons.Response.ErrorResponse;
import com.gomnitrix.commons.configuration.GeneralConfig;
import com.gomnitrix.commons.entity.User;
import com.gomnitrix.commons.exception.AuthenFailedException;
import com.gomnitrix.commons.service.BlogUserService;
import com.gomnitrix.fariauth.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Map;

@Configuration
public class FariTokenEndpointAuthenticationFilter extends TokenEndpointAuthenticationFilter {
    @Autowired
    BlogUserService service;

    public FariTokenEndpointAuthenticationFilter(AuthenticationManager authenticationManager, OAuth2RequestFactory oAuth2RequestFactory) {
        super(authenticationManager, oAuth2RequestFactory);
    }

    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        super.onSuccessfulAuthentication(request, response, authResult);
        User user = (User) authResult.getPrincipal();
        String ip = IpUtils.getIpAddr(request);
        Map<String, String> userMap = IpUtils.getOsAndBrowserInfo(request);
        user.setBrowser(userMap.get(GeneralConfig.BROWSER));
        user.setOs(userMap.get(GeneralConfig.OS));
        user.setLastLoginIp(ip);
        user.setLastLoginTime(LocalDateTime.now());
        service.updateLoginInfoById(user);
    }

    @Override
    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        super.onUnsuccessfulAuthentication(request, response, failed);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ErrorResponse.Builder builder = new ErrorResponse.Builder(new AuthenFailedException());
        if (failed instanceof LockedException) {
            builder.setMessage("账户被锁定，请联系管理员!");
        } else if (failed instanceof CredentialsExpiredException) {
            builder.setMessage("密码过期，请联系管理员!");
        } else if (failed instanceof AccountExpiredException) {
            builder.setMessage("账户过期，请联系管理员!");
        } else if (failed instanceof DisabledException) {
            builder.setMessage("账户被禁用，请联系管理员!");
        } else if (failed instanceof BadCredentialsException) {
            builder.setMessage("用户名或者密码输入错误，请重新输入!");
        }
        out.write(builder.build().toJson());
        out.flush();
        out.close();
    }
}
