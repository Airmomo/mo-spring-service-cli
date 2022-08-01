package com.mospring.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 错误页面配置
 */
@Configuration
public class ErrorPageConfiguration implements ErrorPageRegistrar {

    /**
     * 注册错误页面（状态码，页面路由）
     */
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage page_400 = new ErrorPage(HttpStatus.BAD_REQUEST,"/bad_request");
        ErrorPage page_404 = new ErrorPage(HttpStatus.NOT_FOUND,"/not_found");
        ErrorPage page_500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/internal_server_error");
        registry.addErrorPages(page_400,page_404,page_500);
    }
}
