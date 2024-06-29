package com.lilystu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyErrorController {

    @GetMapping("/err")
    //模拟服务端异常
    public String err(){
        int i = 10/0;
        //默认处理机制--显示500页面
        //配置异常处理器，就会交由处理处理
        return "manage";
    }

    @PostMapping("/err2")
    //模拟客户端异常，客户端使用GET方式访问
    public String err2(){
        return "manage";
    }
}
