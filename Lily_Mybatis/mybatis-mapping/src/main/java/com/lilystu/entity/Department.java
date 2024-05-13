package com.lilystu.entity;

public class Department {
    private int id;
    private String dept_name;

    public Department(int id, String dept_name) {
        this.id = id;
        this.dept_name = dept_name;
    }

    public Department() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", dept_name='" + dept_name + '\'' +
                '}';
    }

    /*
    create table department
    {
    id INT primary key auto_increment，
    dept_name varchar(32) not null default ''
    }CHARSET utf8;
     */
}
