package com.lilystu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
//@Controller//请求视图
public class MonsterController {

//    @RequestMapping(value = "/monster", method = RequestMethod.GET)//等价写法
    @GetMapping("/monster")
    public String getMonster() {
        return "GET-查询妖怪！";
    }

    @PostMapping("/monster")
    public String saveMonster() {
        return "POST-保存妖怪！";
    }

    @DeleteMapping("/monster")
    public String delMonster() {
        return "DELETE-删除妖怪！";
    }

    @PutMapping("/monster")
    public String putMonster() {
        return "PUT-修改妖怪！";
    }

    @RequestMapping("/go")
    public String go() {
        //如果配置了视图解析器，就会去请求页面
        //如果不配置视图解析器就会尝试请求/hello
        return "hello";
    }
}
