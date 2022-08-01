package com.mospring.config;

import com.mospring.service.AccountService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Spring Security 配置类
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    AccountService service;
    @Resource
    PersistentTokenRepository persistentTokenRepository;

    /**
     * 若需要在子线程中获取SecurityContext
     * 那么需要修改SecurityContextHolder的存储策略为MODE_INHERITABLETHREADLOCAL
     */
    @PostConstruct
    public void init(){
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    /**
     * Spring Security 用户认证配置
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        auth
                .userDetailsService(service)
                .passwordEncoder(encoder);
    }

    /**
     * Spring Security 用户访问配置
     * 基于角色进行授权
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //用户角色
        String[] roles = {"admin","user"};
        //请求权限配置
        http
                .authorizeRequests()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/login.do").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/register.do").permitAll()
                .anyRequest().hasAnyRole(roles)
                .and()
                .formLogin()
                .loginPage("/login")              //自定义登陆页面
                .loginProcessingUrl("/login.do")  //登陆请求地址
                .failureForwardUrl("/login/error")//登陆转发请求地址
                .successForwardUrl("/index")      //登陆成功转发请求地址
                .and()
                .logout()
                .logoutUrl("/logout")             //退出登陆请求地址
                .logoutSuccessUrl("/login");      //成功退出登陆后请求地址
        //配置记住我功能
        http
                .rememberMe()
                .rememberMeParameter("remember-me")        //配置判断是否记住我的字段名
                .tokenRepository(persistentTokenRepository)//配置用于存放记录的Token仓库（用数据库表来存储）
                .tokenValiditySeconds(60 * 60 * 24 * 7);   //配置Token的有效时间
        //配置CSRF防御
        http
                .csrf()
                .disable();//关闭CSRF-Token功能
    }
}
