package com.mospring.controller.page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "专门用于返回自定义的错误页面")
@ApiIgnore
@Controller
public class ErrorPageController {

    @ApiOperation("400：请求错误")
    @RequestMapping("/bad_request")
    public String bad_request(){
        return "error/400";
    }

    @ApiOperation("404：服务器未找到请求的资源")
    @RequestMapping("/not_found")
    public String not_found(){
        return "error/404";
    }

    @ApiOperation("500：服务器内部运行出错")
    @RequestMapping("/internal_server_error")
    public String internal_server_error(){
        return "error/500";
    }

}
