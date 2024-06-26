package com.lilystu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    //跳转登录界面
    @GetMapping({"/","/login"})
    public String login(){
        return "adminLogin";//引入了starter-thymeleaf依赖，无需配置视图解析器，会去Classpath:/templates/文件夹下找对应的html文件
    }
}
