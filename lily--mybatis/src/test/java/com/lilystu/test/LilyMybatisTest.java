package com.lilystu.test;

import com.lilystu.entity.Monster;
import com.lilystu.lilymybatis.sqlsession.*;
import com.lilystu.lilymybatis.sqlsession.config.MapperBean;
import com.lilystu.mapper.MonsterMapper;
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
        Monster monster = executor.query("select * from monster where id = ?", 1);
        System.out.println("monster= " + monster);
    }

    @Test
    public void testLilySqlSession(){
        LilySqlSession lilySqlSession = new LilySqlSession();
        Monster monster = lilySqlSession
                .selectOne("select * from monster where id=?", 1);
        System.out.println("monster== " + monster);
    }

    @Test
    public void testReadMapper(){
        LilyConfiguration lilyConfiguration = new LilyConfiguration();
        MapperBean mapperBean = lilyConfiguration.readMapper("MonsterMapper.xml");
        System.out.println("mapperBean = " + mapperBean);
    }

    @Test
    public void testGetMapper(){
        LilySqlSession lilySqlSession = new LilySqlSession();
        MonsterMapper mapper = lilySqlSession.getMapper(MonsterMapper.class);
        System.out.println("mapper--" + mapper);
        Monster monster = mapper.getMonsterById(1);
        System.out.println("mapper--" + mapper.getClass());
        System.out.println("monster--" + monster);
    }

    @Test
    public void testSessionFactory(){
        LilySqlSession lilySqlSession = LilySessionFactory.open();
        MonsterMapper mapper = lilySqlSession.getMapper(MonsterMapper.class);
        Monster monster = mapper.getMonsterById(1);
        System.out.println("monster--" + monster);
    }
}
