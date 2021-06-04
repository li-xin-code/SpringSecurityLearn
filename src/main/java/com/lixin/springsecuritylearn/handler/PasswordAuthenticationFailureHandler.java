package com.lixin.springsecuritylearn.handler;

import com.alibaba.fastjson.JSON;
import com.lixin.springsecuritylearn.model.result.HttpResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户名&密码登录失败处理器
 *
 * @author lx
 * @date 2021/6/3
 */
@Component
public class PasswordAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        HttpResult result = HttpResult.builder().code(403).message(exception.getMessage()).build();
        response.getWriter().println(JSON.toJSON(result));
    }
}
