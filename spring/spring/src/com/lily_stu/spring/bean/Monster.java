package com.lily_stu.spring.bean;

public class Monster {
    private Integer id;
    private String name;
    private String skill;

    public Monster(Integer id, String name, String skill) {
        this.id = id;
        this.name = name;
        this.skill = skill;
    }

    public Monster(){//反射创建对象时需要使用
    }

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

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }
}
