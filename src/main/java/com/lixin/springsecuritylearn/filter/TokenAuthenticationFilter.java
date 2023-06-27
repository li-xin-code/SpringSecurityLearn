package com.lixin.springsecuritylearn.filter;

import com.lixin.springsecuritylearn.authentication.password.PasswordAuthenticationToken;
import com.lixin.springsecuritylearn.service.TokenService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 令牌认证过滤器
 * {@link OncePerRequestFilter} 一次请求只过滤一次
 *
 * @author lx
 * @date 2021/6/4
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final UserDetailsService userDetailsService;

    public TokenAuthenticationFilter(@Qualifier("redisTokenService") TokenService tokenService,
                                     @Qualifier("userDetailsService") UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("token");
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = tokenService.searchUsernameByToken(token);
        if (username == null) {
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (userDetails == null) {
            System.out.println("无效令牌");
            filterChain.doFilter(request, response);
            return;
        }

        PasswordAuthenticationToken authenticationToken =
                new PasswordAuthenticationToken(username, "", userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }

}
