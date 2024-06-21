package com.lilystu.springboot;

import com.lilystu.springboot.bean.Monster;
import com.lilystu.springboot.config.BeanConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MainApp {
    public static void main(String[] args) {
        //启动SpringBoot 应用程序
        ApplicationContext ioc = SpringApplication.run(MainApp.class, args);
//        Monster monster = ioc.getBean(Monster.class);
//        System.out.println(monster);

//        BeanConfig config = ioc.getBean(BeanConfig.class);
//        Monster monster1 = config.monster01();
//        Monster monster2 = config.monster01();
//        System.out.println("monster1 = " + monster1.hashCode());
//        System.out.println("monster2 = " + monster2.hashCode());

//        Monster monster01 = ioc.getBean(Monster.class);
//        Monster monster02 = ioc.getBean(Monster.class);
//        System.out.println("monster01 = " + monster01.hashCode());
//        System.out.println("monster02 = " + monster02.hashCode());


    }
}
