package com.lily_stu.spring.aop.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopXMLTest {
    public static void main(String[] args) {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans09.xml");
        SmartAnimal proxy = ioc.getBean(SmartAnimal.class);//获取到的已经是一个代理对象，所以只能用接口获取
        proxy.getSub(10,5);//得到的代理对象是实现SmartAnimal接口的一个对象
        System.out.println("==============================");
        proxy.getSum(10,20);
    }

}
