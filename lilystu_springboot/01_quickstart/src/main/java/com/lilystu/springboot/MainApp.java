package com.lilystu.springboot;

import com.lilystu.springboot.bean.Cat;
import com.lilystu.springboot.bean.Dog;
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

//        System.out.println("======获取@import 注入的组件======");
//        Dog dog = ioc.getBean(Dog.class);
////通过@import 注入的组件, 组件的名字就是全类名
//        String[] beanNamesForType = ioc.getBeanNamesForType(Dog.class);
//        for (String s : beanNamesForType) {
//            System.out.println("s= " + s);
//        }
//        Cat cat = ioc.getBean(Cat.class);
//        System.out.println("dog= " + dog + " cat= " + cat);

//        Dog dog01 = ioc.getBean("dog01",Dog.class);
//        System.out.println(dog01);

        System.out.println("monster03: " + ioc.containsBean("monster03"));
        System.out.println(ioc.getBean("monster03"));
    }
}
