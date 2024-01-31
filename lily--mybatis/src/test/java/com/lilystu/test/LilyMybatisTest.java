package com.lilystu.test;

import com.lilystu.entity.Monster;
import com.lilystu.lilymybatis.sqlsession.Executor;
import com.lilystu.lilymybatis.sqlsession.LilyConfiguration;
import com.lilystu.lilymybatis.sqlsession.LilyExecutor;
import org.junit.Test;

import java.sql.Connection;

public class LilyMybatisTest {
    public static void main(String[] args) {
        LilyConfiguration lilyConfiguration = new LilyConfiguration();
        //获取到一个Connection
        Connection connection = lilyConfiguration.build("hsp_config.xml");
        System.out.println(connection);
    }

    @Test
    public void testLilyExecutor() {
        Executor executor = new LilyExecutor();
        Monster monster = executor.query("select * from monster where id = ?", 2);
        System.out.println("monster= " + monster);
    }
}
