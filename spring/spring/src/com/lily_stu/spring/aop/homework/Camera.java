package com.lily_stu.spring.aop.homework;

import org.springframework.stereotype.Component;

@Component(value = "camera")
public class Camera implements UsbInterface{
    @Override
    public void work(String job) {
        System.out.println("Camera 工作：" + job);
    }
}
