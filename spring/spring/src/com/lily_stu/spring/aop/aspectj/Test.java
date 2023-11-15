package com.lily_stu.spring.aop.aspectj;

import com.lily_stu.spring.aop.homework.UsbInterface;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans06.xml");
        SmartAnimal proxy = ioc.getBean(SmartAnimal.class);//获取到的已经是一个代理对象，所以只能用接口获取
        proxy.getSub(10,5);
        System.out.println("==============================");
        proxy.getSum(10,20);
        System.out.println("==============================");
        UsbInterface phone = ioc.getBean("phone",UsbInterface.class);
        phone.work("打电话");
        System.out.println("==============================");
        UsbInterface camera = ioc.getBean("camera",UsbInterface.class);
        camera.work("拍照片");

    }
}
