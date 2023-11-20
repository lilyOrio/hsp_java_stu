package com.lilystu.spring.component;

import org.springframework.stereotype.Component;

@Component
public class UserDao {
    public void hi() {
        System.out.println("UserDao hi() 被调用...");
    }
}
