package com.lixin.springsecuritylearn.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * 全局配置
 *
 * @author lx
 * @date 2021/6/2
 */
@Configuration
@RequiredArgsConstructor
public class SystemGlobalConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder users = User.builder().passwordEncoder(str -> passwordEncoder().encode(str));
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user")
                .password("password")
                .authorities("look", "ROLE_USER")
                .build());
        manager.createUser(users.username("admin")
                .password("password")
                .authorities("look", "add", "del", "ROLE_USER", "ROLE_ADMIN")
                .build());
        return manager;
    }

}
