package com.lilystu.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @RequestMapping("/1.jpeg")
    public String hi(){
        return "hi~";
    }
}
