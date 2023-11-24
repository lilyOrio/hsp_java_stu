package com.lilystu.spring.component;

import com.lilystu.spring.annotation.Component;
import com.lilystu.spring.processe.InitializingBean;

@Component
public class Car implements InitializingBean {
    @Override
    public void afterPropertiesSet() {
        System.out.println("Car 的初始化方法。。。");
    }
}
