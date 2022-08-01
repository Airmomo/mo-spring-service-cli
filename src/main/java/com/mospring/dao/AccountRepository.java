package com.mospring.dao;

import com.mospring.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JPA：用户表操作映射接口
 */
@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    /**
     * 查询：根据用户名获取用户记录
     */
    Optional<Account> findAccountByUsername(String username);

    /**
     * 删除：根据用户名删除用户记录
     */
    void deleteAccountByUsername(String username);

}
