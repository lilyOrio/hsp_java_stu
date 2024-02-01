package com.lilystu.test;

import com.lilystu.entity.Monster;
import com.lilystu.lilymybatis.sqlsession.Executor;
import com.lilystu.lilymybatis.sqlsession.LilyConfiguration;
import com.lilystu.lilymybatis.sqlsession.LilyExecutor;
import com.lilystu.lilymybatis.sqlsession.LilySqlSession;
import com.lilystu.lilymybatis.sqlsession.config.MapperBean;
import org.junit.Test;

import java.sql.Connection;

public class LilyMybatisTest {
    public static void main(String[] args) {
        LilyConfiguration lilyConfiguration = new LilyConfiguration();
        //获取到一个Connection
        Connection connection = lilyConfiguration.build("lily_config.xml");
        System.out.println(connection);
    }

    @Test
    public void testLilyExecutor() {
        Executor executor = new LilyExecutor();
        Monster monster = executor.query("select * from monster where id = ?", 2);
        System.out.println("monster= " + monster);
    }

    @Test
    public void testLilySqlSession(){
        LilySqlSession lilySqlSession = new LilySqlSession();
        Monster monster = lilySqlSession
                .selectOne("select * from monster where id=?", 2);
        System.out.println("monster== " + monster);
    }

    @Test
    public void testReadMapper(){
        LilyConfiguration lilyConfiguration = new LilyConfiguration();
        MapperBean mapperBean = lilyConfiguration.readMapper("MonsterMapper");
        System.out.println("mapperBean = " + mapperBean);
    }
}
