package com.lixin.springsecuritylearn.handler;

import com.alibaba.fastjson.JSON;
import com.lixin.springsecuritylearn.model.result.HttpResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限异常处理
 *
 * @author lixin
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException {
        response.setStatus(403);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");
        HttpResult result = HttpResult.builder()
                .code(HttpStatus.FORBIDDEN.value())
                .message(accessDeniedException.getMessage()).build();
        response.getWriter().println(JSON.toJSON(result));
    }
}