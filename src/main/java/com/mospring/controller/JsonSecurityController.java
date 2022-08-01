package com.mospring.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 *
 */
@Api(tags = "专门用于返回Spring Security与前端交互的JSON数据")
@ApiIgnore
//@RestController
public class JsonSecurityController {

    @ApiOperation("需要登陆")
    @RequestMapping("/login")
    public JSONObject login(){
        JSONObject object = new JSONObject();
        object.put("status",520);
        object.put("msg","需要登陆");
        return object;
    }

    @ApiOperation("登陆成功")
    @RequestMapping("/index")
    public JSONObject index(){
        JSONObject object = new JSONObject();
        object.put("status",200);
        object.put("msg","登陆成功");
        return object;
    }

    @ApiOperation("登陆失败")
    @RequestMapping("/login/error")
    public JSONObject login_err(){
        JSONObject object = new JSONObject();
        object.put("status",500);
        object.put("msg","登陆失败");
        return object;
    }

}
