package com.lilystu.mapper;

import com.lilystu.entity.User;

public interface UserMapper {
    //通过id 获取User 对象
    public User getUserById(Integer id);
}
