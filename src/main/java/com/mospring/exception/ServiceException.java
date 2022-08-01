package com.mospring.exception;

import com.mospring.enumer.ErrorStatus;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ServiceException extends RuntimeException{

    /**
     * 异常状态码
     */
    private Integer status;
    /**
     * 异常状态码描述
     */
    private String message;

    public ServiceException() {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = "服务执行过程中发生了错误，无法继续执行";
    }

    public ServiceException(String message) {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = message;
    }

    public ServiceException(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ServiceException build(ErrorStatus status){
        return new ServiceException(status.getStatus(),status.getMsg());
    }

}
