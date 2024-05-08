package com.lilystu.entity;

public class User {
    private Integer user_id;
    private String userName;
    private String userEmail;

    public User(Integer user_id, String userName, String userEmail) {
        this.user_id = user_id;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public User() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
