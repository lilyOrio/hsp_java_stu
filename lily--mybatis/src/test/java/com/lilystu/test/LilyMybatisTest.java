package com.lilystu.test;

import com.lilystu.lilymybatis.sqlsession.LilyConfiguration;

import java.sql.Connection;

public class LilyMybatisTest {
    public static void main(String[] args) {
        LilyConfiguration lilyConfiguration = new LilyConfiguration();
        //获取到一个Connection
        Connection connection = lilyConfiguration.build("hsp_config.xml");
        System.out.println(connection);
    }
}
