package com.mospring.serializer;


import io.swagger.annotations.ApiModel;
import org.springframework.http.HttpStatus;

/**
 * 通用异常响应序列化器
 * 统一返回异常信息的JSON格式
 */
@ApiModel(description = "异常响应实体封装类")
public class ExceptionResponseResult extends ResponseResult<Exception> {

    public ExceptionResponseResult(int status, String msg, Exception data) {
        super(status, msg, data,0);
    }

    public static ExceptionResponseResult error(){
        return error("error");
    }

    public static ExceptionResponseResult error(String message){
        return error(message, null);
    }

    public static ExceptionResponseResult error(String message, Exception data){
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, data);
    }

    public static ExceptionResponseResult error(int status, String message){
        return error(status, message, null);
    }

    public static ExceptionResponseResult error(int status, String message,Exception data){
        return new ExceptionResponseResult(status, message, data);
    }

}
