package com.mospring.enumer;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举类：自定义错误状态码和描述
 */
@Getter
@AllArgsConstructor
public enum ErrorStatus {

    /**
     * 请求相关的错误
     */
    PARAM_USERNAME_MISSING(2001,"请求缺少用户名字段"),
    PARAM_PASSWORD_MISSING(2002,"请求缺少用户密码字段"),
    PARAM_SECURITY_CONTEXT_MISSING(2003,"找不到认证上下文，请重新登陆账号"),

    /**
     * 业务操作:用户相关的错误
     */
    ACCOUNT_USERNAME_REPEAT(3001,"注册失败，该用户已存在，用户名已被占用"),
    ACCOUNT_IS_NULL(3002,"请求的用户不存在"),

    /**
     * 数据库操作相关的错误
     */
    MYSQL_OPERATION_ERROR(4001,"MySQL数据库操作错误"),
    REDIS_OPERATION_ERROR(5001,"Redis数据库操作错误");

    private final Integer status;
    private final String msg;

}
