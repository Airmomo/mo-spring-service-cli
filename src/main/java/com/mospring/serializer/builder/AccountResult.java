package com.mospring.serializer.builder;

import com.mospring.entity.Account;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 用户序列化器
 */
@Data
@Component
public class AccountResult {

    private String username;
    private String role;
    private String email;

    /**
     * 构造响应的用户实体，隐藏敏感信息
     */
    public AccountResult build(Account account){
        AccountResult result = new AccountResult();
        result.setUsername(account.getUsername());
        result.setRole(account.getRole());
        result.setEmail(account.getAccountDetail().getEmail());
        return result;
    }

}
