package com.lilystu.springboot.mybatis.controller;

import com.lilystu.springboot.mybatis.bean.Monster;
import com.lilystu.springboot.mybatis.service.MonsterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class MonsterController {

    //装配MonsterService
    @Resource
    private MonsterService monsterService;

    @GetMapping("/getMonster")
    @ResponseBody
    public Monster getMonsterById(@RequestParam(value = "id")Integer id){
        return monsterService.getMonsterById(id);
    }
}
