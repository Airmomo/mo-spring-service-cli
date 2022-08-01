package com.mospring.controller.page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "专门用于返回模版页面")
@ApiIgnore
@Controller
public class PageController {

    @ApiOperation("登陆成功后的主页")
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @ApiOperation("登陆失败")
    @RequestMapping("/login/error")
    public String login_err(){
        return "forward:/login";
    }

    @ApiOperation("登陆用户页面")
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @ApiOperation("注册用户页面")
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

}
