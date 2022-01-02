package com.lixin.springsecuritylearn.config;


import com.lixin.springsecuritylearn.authentication.password.PasswordAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 密码验证配置
 *
 * @author lx
 * @date 2021/6/1
 */
@Order(101)
@Configuration
public class PasswordWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordAuthenticationFilter authenticationFilter;

    public PasswordWebSecurityConfig(PasswordAuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) {
        http.addFilterAfter(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
