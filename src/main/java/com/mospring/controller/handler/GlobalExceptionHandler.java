package com.mospring.controller.handler;

import com.mospring.exception.DatabaseException;
import com.mospring.exception.ParamException;
import com.mospring.exception.ServiceException;
import com.mospring.serializer.ExceptionResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


/**
 * 全局异常响应类（返回json）
 * RestControllerAdvice = @ControllerAdvice + @ResponseBody
 */
@Slf4j
/**
 * 拦截指定包下出现的异常
 */
@RestControllerAdvice(basePackages = {"com.mospring.controller",
                                      "com.mospring.service"})
public class GlobalExceptionHandler {

    /**
     * 请求参数异常
     */
    @ExceptionHandler(ParamException.class)
    public ExceptionResponseResult handleServiceException(ParamException e, HttpServletRequest request)
    {
        log.error(e.getMessage(), e);
        Integer status = e.getStatus();
        return status == null ? ExceptionResponseResult.error(e.getMessage())
                : ExceptionResponseResult.error(status,e.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public ExceptionResponseResult handleServiceException(ServiceException e, HttpServletRequest request)
    {
        log.error(e.getMessage(), e);
        Integer status = e.getStatus();
        return status == null ? ExceptionResponseResult.error(e.getMessage())
                              : ExceptionResponseResult.error(status,e.getMessage());
    }

    /**
     * 数据库操作异常
     */
    @ExceptionHandler(DatabaseException.class)
    public ExceptionResponseResult handleServiceException(DatabaseException e, HttpServletRequest request)
    {
        log.error(e.getMessage(), e);
        Integer status = e.getStatus();
        return status == null ? ExceptionResponseResult.error(e.getMessage())
                : ExceptionResponseResult.error(status,e.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ExceptionResponseResult handleRuntimeException(RuntimeException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}':发生未知异常:", requestURI, e);
        return ExceptionResponseResult.error(e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public ExceptionResponseResult handleException(Exception e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}':发生系统异常:", requestURI, e);
        return ExceptionResponseResult.error(e.getMessage());
    }

}
