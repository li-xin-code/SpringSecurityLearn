package com.lixin.springsecuritylearn.authentication.password;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 用户认证（用户名&密码 认证）
 *
 * @author lx
 * @date 2021/6/2
 */
@Component
public class PasswordAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    public PasswordAuthenticationProvider(
            @Qualifier("userDetailsService") UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 认证用户信息
     *
     * @param authentication 未认证用户信息
     * @return 认证用户信息
     * @throws AuthenticationException 身份验证异常
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("用户不存在");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new InternalAuthenticationServiceException("密码错误");
        }

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        PasswordAuthenticationToken authenticationResult =
                new PasswordAuthenticationToken(username, "", authorities);
        authenticationResult.setDetails(authentication.getDetails());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }

}
