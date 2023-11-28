package com.lilystu.spring.component;

import com.lilystu.spring.annotation.Component;
import com.lilystu.spring.proesse.InitializingBean;

@Component
public class Car implements InitializingBean {
    public void init() {
        System.out.println("Car init ...");
    }
}
