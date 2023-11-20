package com.lilystu.spring.test;

import com.lilystu.spring.aop.SmartAnimalable;
import com.lilystu.spring.component.UserDao;
import com.lilystu.spring.component.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {
    public static void main(String[] args) {
       ApplicationContext ioc =
               new ClassPathXmlApplicationContext("beans.xml");
       System.out.println("========================");
        System.out.println(ioc.getBean("userAction"));
        System.out.println(ioc.getBean("userAction"));

        UserDao userDao = (UserDao) ioc.getBean("userDao");
        System.out.println(userDao);
        UserService userService = (UserService) ioc.getBean("userService");
        System.out.println(userService);

        //====测试依赖注入=======
        userService.m1();

        //====测试aop=========
        SmartAnimalable smartDog = (SmartAnimalable) ioc.getBean("smartDog");
        smartDog.getSub(10, 30);

        //关闭容器
        ((ConfigurableApplicationContext) ioc).close();
    }
}
