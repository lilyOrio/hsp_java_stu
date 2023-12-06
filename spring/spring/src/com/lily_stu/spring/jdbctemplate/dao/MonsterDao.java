package com.lily_stu.spring.jdbctemplate.dao;

import com.lily_stu.spring.bean.Monster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MonsterDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //添加master
    public void save(Monster monster){
        String sql = "INSERT INTO monster VALUES(?, ?, ?)";
        jdbcTemplate.update(sql,monster.getId(),monster.getName(),monster.getSkill());
    }
}
