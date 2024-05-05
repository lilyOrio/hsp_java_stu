package com.lilystu.lilymybatis.sqlsession;

import java.lang.reflect.Proxy;

public class LilySqlSession {
    private final Executor executor = new LilyExecutor();
    private final LilyConfiguration lilyConfiguration = new LilyConfiguration();
    public <T> T selectOne(String statement, Object parameter) {
        return executor.query(statement, parameter);
    }
    public <T> T getMapper(Class clazz){
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},
                new LilyMapperProxy(this,clazz,lilyConfiguration));
    }

}
