package com.lily_stu.spring.aop.homework02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HomeworkTest {
    public static void main(String[] args) {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans09.xml");
        Cal cal = ioc.getBean(Cal.class);
        cal.cal1(3);
        System.out.println("==============");
        cal.cal2(4);
    }
}
