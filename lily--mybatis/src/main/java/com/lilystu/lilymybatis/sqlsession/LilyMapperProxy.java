package com.lilystu.lilymybatis.sqlsession;

import com.lilystu.lilymybatis.sqlsession.config.Function;
import com.lilystu.lilymybatis.sqlsession.config.MapperBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 */
public class LilyMapperProxy implements InvocationHandler {
    private final LilySqlSession sqlSession;
    private final String fileName;
    private final LilyConfiguration configuration;

    public LilyMapperProxy(LilySqlSession sqlSession, Class clazz, LilyConfiguration configuration) {
        this.sqlSession = sqlSession;
        this.fileName = clazz.getSimpleName()+".xml";
        this.configuration = configuration;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperBean mapperBean = configuration.readMapper(this.fileName);
        if (!method.getDeclaringClass().getName().equals(mapperBean.getInterfaceName())){
            return null;
        }
        List<Function> functionList = mapperBean.getList();
        if (functionList==null || functionList.size() == 0){
            return null;
        }
        for (Function function : functionList) {
//            if ("select".equals(function.getSqltype())){
//
//            }
            if (method.getName().equals(function.getFuncName())){
                System.out.println("proxy--" + proxy.getClass());
                return sqlSession.selectOne(function.getSql(),args[0]);
            }
        }
        return null;
    }
}
