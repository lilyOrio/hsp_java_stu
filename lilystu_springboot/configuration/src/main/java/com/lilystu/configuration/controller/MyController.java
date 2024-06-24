package com.lilystu.configuration.controller;

import com.lilystu.configuration.bean.Monster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class MyController {

    @Resource
    private Monster monster;

    @RequestMapping("/monster")
    public Monster getMonster(){
        log.info("monster = " , monster);
        return monster;
    }
}
