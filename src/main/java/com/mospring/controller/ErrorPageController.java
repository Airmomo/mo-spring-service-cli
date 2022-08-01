package com.mospring.controller;

import com.mospring.serializer.ExceptionResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "专门用于返回自定义的错误页面")
@ApiIgnore
@RestController
public class ErrorPageController {

    @ApiOperation("400：请求错误")
    @RequestMapping("/bad_request")
    public ExceptionResponseResult bad_request(){
        return ExceptionResponseResult.error(400,"400：请求错误");
    }

    @ApiOperation("404：服务器未找到请求的资源")
    @RequestMapping("/not_found")
    public ExceptionResponseResult not_found(){
        return ExceptionResponseResult.error(404,"404：服务器未找到请求的资源");
    }

    @ApiOperation("500：服务器内部运行出错")
    @RequestMapping("/internal_server_error")
    public ExceptionResponseResult internal_server_error(){
        return ExceptionResponseResult.error(500,"500：服务器内部运行出错");
    }

}
