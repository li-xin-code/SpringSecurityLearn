package com.lixin.springsecuritylearn.authentication.password;

import com.alibaba.fastjson.JSON;
import com.lixin.springsecuritylearn.model.form.UsernamePasswordLoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 验证过滤器（用户名&密码）
 *
 * @author lx
 * @date 2021/6/2
 */
@Component
public class PasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    PasswordAuthenticationFilter(
            @Qualifier("passwordAuthenticationSuccessHandler")
                    SimpleUrlAuthenticationSuccessHandler successHandler,
            @Qualifier("passwordAuthenticationFailureHandler")
                    SimpleUrlAuthenticationFailureHandler failureHandler) {
        // 根据RequestMatcher，判断是否需要进行认证处理。
        // 每个filter实现类，都需要传入一个处理的url路径，当我们的请求match这个路径时，才会被该filter处理
        super(new AntPathRequestMatcher("/login", "POST"));
        super.setAuthenticationSuccessHandler(successHandler);
        super.setAuthenticationFailureHandler(failureHandler);
    }

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse)
            throws AuthenticationException {
        UsernamePasswordLoginForm loginForm = obtainForm(httpServletRequest);
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        checkUsername(username);
        checkPassword(password);
        PasswordAuthenticationToken authRequest = new PasswordAuthenticationToken(username, password);

        setDetails(httpServletRequest, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private void setDetails(HttpServletRequest request,
                            PasswordAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    /**
     * 读取&解析 HttpServletRequest 中的登录表单
     *
     * @param request HttpServletRequest
     * @return UsernamePasswordLoginForm 登录表单
     */
    private UsernamePasswordLoginForm obtainForm(HttpServletRequest request) {
        BufferedReader reader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            reader = request.getReader();
            String oneLine;
            while ((oneLine = reader.readLine()) != null) {
                stringBuilder.append(oneLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String context = stringBuilder.toString();
        return JSON.parseObject(context, UsernamePasswordLoginForm.class);
    }

    /**
     * 检查用户名合法性
     *
     * @param username 用户名
     */
    private void checkUsername(String username) {
        if (!StringUtils.hasText(username)) {
            throw new InternalAuthenticationServiceException("用户名不能为空");
        }
    }

    /**
     * 检查密码合法性
     *
     * @param password 密码
     */
    private void checkPassword(String password) {
        if (!StringUtils.hasText(password)) {
            throw new InternalAuthenticationServiceException("密码不能为空");
        }
    }

}
