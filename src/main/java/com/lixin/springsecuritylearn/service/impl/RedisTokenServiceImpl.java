package com.lixin.springsecuritylearn.service.impl;

import com.lixin.springsecuritylearn.service.TokenService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Redis存储token
 *
 * @author lx
 * @date 2021/6/3
 */
@Service("redisTokenService")
@CacheConfig(cacheNames = {"access-token"})
public class RedisTokenServiceImpl implements TokenService {

    @Override
    @Cacheable(key = "#token")
    public String saveToken(String token, String username) {
        return username;
    }

    @Override
    @Cacheable(key = "#token")
    public String searchUsernameByToken(String token) {
        return "";
    }

    @Override
    @CacheEvict(key = "#token")
    public void delete(String token) {

    }
}
