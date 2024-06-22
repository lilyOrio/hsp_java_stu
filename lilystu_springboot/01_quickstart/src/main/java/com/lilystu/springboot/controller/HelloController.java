package com.lilystu.springboot.controller;

import com.lilystu.springboot.bean.Furn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Value("${my.website}")
    private String bdUrl;
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello, spring boot \n" + bdUrl;
    }

    @Autowired
    Furn furn;
    @RequestMapping("/furn")
    @ResponseBody
    public Furn furn(){
        return furn;
    }
}