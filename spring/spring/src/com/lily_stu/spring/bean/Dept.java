package com.lily_stu.spring.bean;

/**
 * @author lily
 * @version 1.0
 * 部门类
 */
public class Dept {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "name='" + name + '\'' +
                '}';
    }
}
