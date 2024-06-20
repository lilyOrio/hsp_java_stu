package com.lilystu.springboot;

import com.lilystu.springboot.bean.Monster;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MainApp {
    public static void main(String[] args) {
        //启动SpringBoot 应用程序
        ApplicationContext ioc = SpringApplication.run(MainApp.class, args);
        Monster monster = ioc.getBean(Monster.class);
        System.out.println(monster);

    }
}
