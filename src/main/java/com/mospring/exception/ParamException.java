package com.mospring.exception;

import com.mospring.enumer.ErrorStatus;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 自定义异常：请求参数异常
 */
@Data
public class ParamException extends RuntimeException{

    /**
     * 异常状态码
     */
    private Integer status;
    /**
     * 异常状态码描述
     */
    private String message;

    public ParamException() {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = "请求缺少参数或参数校验导致了错误的发生，无法继续执行";
    }

    public ParamException(String message) {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = message;
    }

    public ParamException(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * 通过枚举类来构造异常
     */
    public static ParamException build(ErrorStatus status){
        return new ParamException(status.getStatus(),status.getMsg());
    }

}
