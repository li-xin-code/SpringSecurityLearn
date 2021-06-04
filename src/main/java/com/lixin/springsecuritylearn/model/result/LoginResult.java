package com.lixin.springsecuritylearn.model.result;

import lombok.Data;

import java.util.List;

/**
 * @author lx
 * @date 2021/6/4
 */
@Data
public class LoginResult {

    /**
     * 用户名
     */
    private String username;

    /**
     * 令牌
     */
    private String accessToken;

    /**
     * 角色列表
     */
    private List<String> roles;

    /**
     * 权限列表
     */
    private List<String> permissions;

}
