package com.lily_stu.spring.jdbctemplate;

import com.lily_stu.spring.bean.Monster;
import com.lily_stu.spring.jdbctemplate.dao.MonsterDao;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcTemplateTest {

    @Test
    public void operDataByDao() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        MonsterDao dao = ioc.getBean(MonsterDao.class);
        Monster monster = new Monster(1000,"大虾精", "夹子功");
        dao.save(monster);
    }
}
