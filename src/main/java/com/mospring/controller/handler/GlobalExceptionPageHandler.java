package com.mospring.controller.handler;

import com.mospring.exception.DatabaseException;
import com.mospring.exception.ParamException;
import com.mospring.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常响应类（返回模版网页和信息）
 */
@Slf4j
@ControllerAdvice(basePackages = {"com.mospring.controller",
                                  "com.mospring.service"})
public class GlobalExceptionPageHandler {

    /**
     * 请求参数异常
     */
    @ExceptionHandler(ParamException.class)
    public String handleServiceException(ParamException e, HttpServletRequest request, Model model)
    {
        log.error(e.getMessage(), e);
        model.addAttribute("error_status",e.getStatus());
        model.addAttribute("error_msg",e.getMessage());
        return "error/error";
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public String handleServiceException(ServiceException e, HttpServletRequest request, Model model)
    {
        log.error(e.getMessage(), e);
        model.addAttribute("error_status",e.getStatus());
        model.addAttribute("error_msg",e.getMessage());
        return "error/error";
    }

    /**
     * 数据库操作异常
     */
    @ExceptionHandler(DatabaseException.class)
    public String handleServiceException(DatabaseException e, HttpServletRequest request, Model model)
    {
        log.error(e.getMessage(), e);
        model.addAttribute("error_status",e.getStatus());
        model.addAttribute("error_msg",e.getMessage());
        return "error/error";
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e, HttpServletRequest request, Model model)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}':发生未知异常:", requestURI, e);
        model.addAttribute("error_status",500);
        model.addAttribute("error_msg",e.getMessage());
        return "error/error";
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request, Model model)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}':发生系统异常:", requestURI, e);
        model.addAttribute("error_status",500);
        model.addAttribute("error_msg",e.getMessage());
        return "error/error";
    }

}
