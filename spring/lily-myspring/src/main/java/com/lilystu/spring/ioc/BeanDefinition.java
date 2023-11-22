package com.lilystu.spring.ioc;

public class BeanDefinition {
    private Class clazz; //bean 对应的Class 对象
    private String scope;//bean 的作用域singleton/prototype

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
