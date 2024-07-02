package com.lilystu.springboot.mybatis;

import com.lilystu.springboot.mybatis.bean.Monster;
import com.lilystu.springboot.mybatis.dao.MonsterMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

@SpringBootTest
public class ApplicationTest {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private MonsterMapper monsterMapper;

    @Test
    public void t1(){
        System.out.println(jdbcTemplate.getDataSource().getClass());
    }

    @Test
    public void getMonsterById(){
        Monster monster = monsterMapper.getMonsterById(1);
        System.out.println("monster = " + monster);
    }
}
