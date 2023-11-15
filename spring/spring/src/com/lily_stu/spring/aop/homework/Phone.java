package com.lily_stu.spring.aop.homework;

import org.springframework.stereotype.Component;

@Component(value = "phone")
public class Phone implements UsbInterface{
    @Override
    public void work(String job) {
        System.out.println("Phone 工作：" +job);
    }
}
