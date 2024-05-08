package com.lilystu.mapper;

import com.lilystu.entity.User;

import java.util.List;

public interface UserMapper {
    //添加方法
    public void addUser(User user);
    //查询所有的User
    public List<User> findAllUser();
}
