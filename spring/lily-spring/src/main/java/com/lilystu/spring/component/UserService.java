package com.lilystu.spring.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserService {
    @Autowired
    private UserDao userDao;

    public void m1() {
        userDao.hi();
    }

    @PostConstruct
    public void init(){
        System.out.println("UserService 初始化业务...");
    }
}
