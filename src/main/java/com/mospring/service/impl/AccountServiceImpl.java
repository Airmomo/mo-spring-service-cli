package com.mospring.service.impl;

import com.mospring.dao.AccountRepository;
import com.mospring.entity.Account;
import com.mospring.entity.AccountDetail;
import com.mospring.enumer.ErrorStatus;
import com.mospring.exception.ServiceException;
import com.mospring.service.AccountService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 服务：用户相关事务
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    AccountRepository repository;

    /**
     * 校验用户名是否重复
     */
    public boolean isUsernameRepeated(String username){
        Account account = repository.findAccountByUsername(username).orElse(null);
        return account != null;
    }

    /**
     * 基于Spring Security实现的用户登陆认证服务
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repository.findAccountByUsername(username).orElse(null);
        if(account == null)
            throw new UsernameNotFoundException("登陆的用户不存在，请注册后重新登陆");
        return User
                .withUsername(account.getUsername())
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }

    /**
     * 根据用户名获取用户详细信息
     */
    public Account getAccountByUsername(String username) {
        Account account = repository.findAccountByUsername(username).orElse(null);
        if (account == null) {
            throw ServiceException.build(ErrorStatus.ACCOUNT_IS_NULL);
        }
        return account;
    }

    /**
     * 注册新用户
     */
    public void registerUser(String username, String password) {
        if(isUsernameRepeated(username)){
            throw ServiceException.build(ErrorStatus.ACCOUNT_USERNAME_REPEAT);
        }
        password = new BCryptPasswordEncoder().encode(password);
        Account account = new Account();
        AccountDetail accountDetail = new AccountDetail();
        account.setUsername(username);
        account.setPassword(password);
        account.setRole("user");
        account.setAccountDetail(accountDetail);
        repository.save(account);
    }

    /**
     * 根据用户名删除用户
     */
    public void deleteAccountByUsername(String username){
        repository.deleteAccountByUsername(username);
    }

}
