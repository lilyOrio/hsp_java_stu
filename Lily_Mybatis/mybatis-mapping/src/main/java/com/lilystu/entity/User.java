package com.lilystu.entity;

import java.util.List;

public class User {
    private Integer id;
    private String name;
    //一个用户可以对应多个宠物
    private List<Pet> pets;

    public User(Integer id, String name, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.pets = pets;
    }

    public User() {
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

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", pets=" + pets +
//                '}';
//    }
}
