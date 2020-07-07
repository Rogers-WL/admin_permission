package com.rog.adminpermission.controller.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description: TODO
 * @Author Rogers
 * @Date 2020/6/22 20:32
 **/
@Api(tags = "用户登录接口")
@ControllerAdvice
public class LoginController {
    @ApiOperation("首页接口")
    @GetMapping("/home")
    public String home(){
        return "首页";
    }
}
