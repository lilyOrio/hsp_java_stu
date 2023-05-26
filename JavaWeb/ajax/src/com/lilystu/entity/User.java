package com.lilystu.entity;

public class User {
    private Integer id;
    private String userName;
    private String email;
    private String pwd;

    public User(Integer id, String userName, String email, String pwd) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.pwd = pwd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
