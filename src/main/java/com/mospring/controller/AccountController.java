package com.mospring.controller;

import com.mospring.config.SwaggerConfiguration;
import com.mospring.entity.Account;
import com.mospring.enumer.ErrorStatus;
import com.mospring.exception.ParamException;
import com.mospring.serializer.builder.AccountResult;
import com.mospring.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * API：用户相关操作
 */
@Api(tags = {SwaggerConfiguration.TAG_ACCOUNT})
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    AccountService service;
    @Resource
    AccountResult result;

    /**
     * 登陆用户("/login.do")和退出登陆("/logout")已通过Spring Security实现
     */

    @ApiOperation("注册用户")
    @PostMapping(value = "/register.do")
    public void doRegister(@ApiParam("用户名") @RequestParam String username,
                           @ApiParam("用户密码") @RequestParam String password){
        if(username.trim().equals("")){
            throw ParamException.build(ErrorStatus.PARAM_USERNAME_MISSING);
        }
        if(password.trim().equals("")){
            throw ParamException.build(ErrorStatus.PARAM_PASSWORD_MISSING);
        }
        service.registerUser(username,password);
    }

    @ApiOperation("获取当前用户的详细信息")
    @GetMapping(value = "/detail")
    public AccountResult getAccount(){
        User user;
        try{
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();
            user = (User) authentication.getPrincipal();
        }catch (Exception e){
            throw ParamException.build(ErrorStatus.PARAM_SECURITY_CONTEXT_MISSING);
        }
        Account account = service.getAccountByUsername(user.getUsername());
        return result.build(account);
    }

    @ApiOperation("根据用户名获取用户信息")
    @GetMapping(value = "/{username}")
    public AccountResult getAccountByUsername(@ApiParam("用户名") @PathVariable("username") String username){
        if(username.trim().equals("")){
            throw ParamException.build(ErrorStatus.PARAM_USERNAME_MISSING);
        }
        Account account = service.getAccountByUsername(username);
        return result.build(account);
    }

    @ApiOperation("删除当前用户")
    @DeleteMapping(value = "/delete")
    public void deleteAccount(){
        User user;
        try{
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();
            user = (User) authentication.getPrincipal();
        }catch (Exception e){
            throw ParamException.build(ErrorStatus.PARAM_SECURITY_CONTEXT_MISSING);
        }
        service.deleteAccountByUsername(user.getUsername());
    }

}
