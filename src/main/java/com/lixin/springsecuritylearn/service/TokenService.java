package com.lixin.springsecuritylearn.service;

/**
 * @author lx
 * @date 2021/6/3
 */
public interface TokenService {

    /**
     * 保存令牌
     *
     * @param token    令牌
     * @param username 用户名
     * @return 令牌
     */
    String saveToken(String token, String username);

    /**
     * 根据令牌查找用户名
     *
     * @param token 令牌
     * @return 令牌
     */
    String searchUsernameByToken(String token);

    /**
     * 删除令牌
     *
     * @param token 令牌
     */
    void delete(String token);

}
