package com.lilystu.lilymybatis.sqlsession;

/**
 * 执行器
 */
public interface Executor {
    public <T> T query(String statement, Object parameter);
}
