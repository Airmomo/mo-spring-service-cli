package com.mospring.exception;

import com.mospring.enumer.ErrorStatus;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 自定义异常：数据库操作异常
 */
@Data
public class DatabaseException extends RuntimeException {

    /**
     * 异常状态码
     */
    private Integer status;
    /**
     * 异常状态码描述
     */
    private String message;

    public DatabaseException() {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = "请求缺少参数或参数校验导致了错误的发生，无法继续执行";
    }

    public DatabaseException(String message) {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = message;
    }

    public DatabaseException(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * 通过枚举类来构造异常
     */
    public static DatabaseException build(ErrorStatus status){
        return new DatabaseException(status.getStatus(),status.getMsg());
    }

}
