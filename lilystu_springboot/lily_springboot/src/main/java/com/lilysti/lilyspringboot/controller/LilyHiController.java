package com.lilysti.lilyspringboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LilyHiController {

    @RequestMapping("/lilyhi")
    public String hi(){
        return "hi, controller";
    }
}
