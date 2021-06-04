package com.lixin.springsecuritylearn.authentication.password;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 用户基本信息存储类 (用户名&密码)
 *
 * @author lx
 * @date 2021/6/2
 */
public class PasswordAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 3602L;

    /**
     * 用户名
     */
    private final String username;

    /**
     * 密码
     */
    private final String password;

    /**
     * 构造未认证用户
     *
     * @param username 用户名
     * @param password 密码
     */
    public PasswordAuthenticationToken(String username, String password) {
        super(null);
        this.username = username;
        this.password = password;
        super.setAuthenticated(false);
    }

    /**
     * 构造已认证用户
     *
     * @param username    用户信息
     * @param password    证书信息
     * @param authorities 权限列表
     */
    public PasswordAuthenticationToken(String username, String password,
                                       Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.username = username;
        this.password = password;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

}
