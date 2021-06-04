package com.lixin.springsecuritylearn.model.form;

import lombok.Data;

/**
 * 用户名密码登录表单
 *
 * @author lx
 * @date 2021/6/1
 */
@Data
public class UsernamePasswordLoginForm {

    /**
     * 用户名
     */
    public String username;

    /**
     * 密码
     */
    public String password;

}
