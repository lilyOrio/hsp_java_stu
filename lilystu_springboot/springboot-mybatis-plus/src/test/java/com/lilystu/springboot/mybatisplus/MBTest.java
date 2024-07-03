package com.lilystu.springboot.mybatisplus;

import com.lilystu.springboot.mybatisplus.bean.Monster;
import com.lilystu.springboot.mybatisplus.dao.MonsterMapper;
import com.lilystu.springboot.mybatisplus.service.MonsterService;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

@SpringBootTest
public class MBTest {

    @Resource
    private MonsterMapper monsterMapper;

    @Resource
    private MonsterService monsterService;

    @Test
    public void t1(){
        Monster monster = monsterMapper.selectById(1);
        System.out.println("monster = " +  monster);
    }

    @Test
    public void t2(){
        Monster monster = monsterService.getById(1);
        System.out.println("monster = " +  monster);
        System.out.println("monsterService.getClass = " +  monsterService.getClass());
    }
}
