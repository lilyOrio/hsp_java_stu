package com.lilystu.text;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class T1BatisSpring {
    @Test
    public void t1() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取bean
        System.out.println(ioc.getBean("pooledDataSource"));
        System.out.println(ioc.getBean("sqlSessionFactory"));
    }
}
