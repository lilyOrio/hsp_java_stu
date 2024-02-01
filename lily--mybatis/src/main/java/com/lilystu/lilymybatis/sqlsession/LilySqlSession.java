package com.lilystu.lilymybatis.sqlsession;

public class LilySqlSession {
    private Executor executor = new LilyExecutor();
    private LilyConfiguration lilyConfiguration = new LilyConfiguration();
    public <T> T selectOne(String statement, Object parameter) {
        return executor.query(statement, parameter);
    }


}
