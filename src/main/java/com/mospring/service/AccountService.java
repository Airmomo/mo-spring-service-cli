package com.mospring.service;

import com.mospring.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 接口定义：用户相关服务
 */
public interface AccountService extends UserDetailsService {

    /**
     * 根据用户名获取用户详细信息
     */
    Account getAccountByUsername(String username);

    /**
     * 注册新用户
     */
    void registerUser(String username, String password);

    /**
     * 根据用户名删除用户
     */
    void deleteAccountByUsername(String username);

}
