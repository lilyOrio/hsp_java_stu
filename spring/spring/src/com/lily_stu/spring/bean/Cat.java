package com.lily_stu.spring.bean;

/**
 * @author lily
 * @version 1.0
 * bean的单例和多例
 */
public class Cat {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
