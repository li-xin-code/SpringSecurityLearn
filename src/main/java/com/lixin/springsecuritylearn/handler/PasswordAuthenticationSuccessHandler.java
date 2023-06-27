package com.lixin.springsecuritylearn.handler;

import com.alibaba.fastjson.JSON;
import com.lixin.springsecuritylearn.model.result.HttpResult;
import com.lixin.springsecuritylearn.model.result.LoginResult;
import com.lixin.springsecuritylearn.service.TokenService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 用户名&密码登录成功处理器
 *
 * @author lx
 * @date 2021/6/2
 */
@Component
public class PasswordAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final TokenService tokenService;

    public PasswordAuthenticationSuccessHandler(@Qualifier("redisTokenService") TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");

        String username = authentication.getName();
        String token = UUID.randomUUID().toString().replaceAll("-", "");

        Objects.requireNonNull(tokenService.saveToken(token, username));

        final String rolePrefix = "ROLE_";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<>(10);
        List<String> permissions = new ArrayList<>(10);
        authorities.forEach(a -> {
            String authority = a.getAuthority();
            if (authority.startsWith(rolePrefix)) {
                roles.add(authority);
            } else {
                permissions.add(authority);
            }
        });


        LoginResult loginResult = new LoginResult();
        loginResult.setUsername(username);
        loginResult.setAccessToken(token);
        loginResult.setRoles(roles);
        loginResult.setPermissions(permissions);

        HttpResult result = HttpResult.builder().data(loginResult).build();
        response.getWriter().println(JSON.toJSON(result));
    }

}
