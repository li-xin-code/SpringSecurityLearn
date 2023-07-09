package com.lixin.springsecuritylearn.handler;

import com.alibaba.fastjson.JSON;
import com.lixin.springsecuritylearn.model.result.HttpResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证异常处理
 *
 * @author lixin
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        HttpResult result = HttpResult.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .message(authException.getMessage()).build();
        response.getWriter().println(JSON.toJSON(result));
    }
}