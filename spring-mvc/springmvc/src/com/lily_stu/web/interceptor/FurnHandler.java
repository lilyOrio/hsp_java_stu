package com.lily_stu.web.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FurnHandler {
    @RequestMapping(value = "/hi")
    public String hi() {
        System.out.println("FurnHandler hi()");
        return "success";
    }
    @RequestMapping(value = "/hello")
    public String hello() {
        System.out.println("FurnHandler hello()");
        return "success";
    }
}
