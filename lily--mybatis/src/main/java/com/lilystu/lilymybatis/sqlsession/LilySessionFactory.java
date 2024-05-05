package com.lilystu.lilymybatis.sqlsession;

public class LilySessionFactory {
    public static LilySqlSession open(){
        return new LilySqlSession();
    }
}
