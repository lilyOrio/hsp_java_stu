package com.lilystu.springboot.controller;

import com.lilystu.springboot.bean.Furn;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class DruidSqlController {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/sql")
    @ResponseBody
    public List<Furn> crudDB() {
        BeanPropertyRowMapper<Furn> rowMapper =
                new BeanPropertyRowMapper<>(Furn.class);

        List<Furn> query = jdbcTemplate.query("SELECT * FROM `furn`", rowMapper);
        for (Furn furn : query) {
            System.out.println(furn);
        }

        //class com.zaxxer.hikari.HikariDataSource  默认数据源
        System.out.println(jdbcTemplate.getDataSource().getClass());
        return query;
    }
}
