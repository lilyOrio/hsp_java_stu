package com.lilystu.service;

import com.lilystu.dao.UserDao;
import com.lilystu.entity.User;

/**
 * @author lily
 * @version 1.0
 * 提供业务方法
 * getUserByName。。。
 */
public class UserService {
    private UserDao mUserDao = new UserDao();

    public User getUserByName(String uname) {
        //
        User user = mUserDao.querySingle
                ("select * from `user` where username=?", User.class, uname);
        return user;
    }
}
