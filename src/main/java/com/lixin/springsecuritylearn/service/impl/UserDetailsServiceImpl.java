package com.lixin.springsecuritylearn.service.impl;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author lx
 * @date 2021/6/2
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUser(username);
    }

    /**
     * 在内存中静态查找用户
     * @param username 用户名
     * @return 用户
     */
    private UserDetails findUser(String username) {
        HashMap<String, User> map = new HashMap<>(16);
        String password = passwordEncoder.encode("123456");
        User user = new User("lixin", password,
                AuthorityUtils.commaSeparatedStringToAuthorityList("look,ROLE_USER"));
        User admin = new User("admin", password,
                AuthorityUtils.commaSeparatedStringToAuthorityList("look,add,del,ROLE_ADMIN"));
        map.put(admin.getUsername(), admin);
        map.put(user.getUsername(), user);
        return map.get(username);
    }

}
