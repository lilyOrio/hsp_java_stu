package com.lily_stu.web.json;

public class Dog {
    private String name;
    private String address;

    public Dog(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Dog() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
