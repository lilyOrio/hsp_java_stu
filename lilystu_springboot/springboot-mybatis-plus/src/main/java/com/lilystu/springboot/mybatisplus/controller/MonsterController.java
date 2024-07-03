package com.lilystu.springboot.mybatisplus.controller;

import com.lilystu.springboot.mybatisplus.bean.Monster;
import com.lilystu.springboot.mybatisplus.service.MonsterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class MonsterController {

    @Resource
    MonsterService monsterService;

    @GetMapping("/monster")
    @ResponseBody
    public Monster getMonsterById(@RequestParam(value = "id") Integer id) {
        return monsterService.getById(id);
    }

    @GetMapping("/monster_list")
    @ResponseBody
    public List<Monster> getMonsterList(){
        return monsterService.list();
    }
}
