package com.lilystu.springboot;

import com.lilystu.springboot.bean.Furn;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ApplicationTests {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void text1(){
        BeanPropertyRowMapper<Furn> rowMapper =
                new BeanPropertyRowMapper<>(Furn.class);

        List<Furn> query = jdbcTemplate.query("SELECT * FROM `furn`", rowMapper);
        for (Furn furn : query) {
            System.out.println(furn);
        }

        //class com.zaxxer.hikari.HikariDataSource  默认数据源
        System.out.println(jdbcTemplate.getDataSource().getClass());
    }
}
