package com.lilystu.entity;

public class Pet {
    private Integer id;
    private String nickname;
    //一个宠物只能对应一个主人User
    private User user;

    public Pet() {
    }

    public Pet(Integer id, String nickname, User user) {
        this.id = id;
        this.nickname = nickname;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
//  会造成栈溢出
//    @Override
//    public String toString() {
//        return "Pet{" +
//                "id=" + id +
//                ", nickname='" + nickname + '\'' +
//                ", user=" + user +
//                '}';
//    }
}
