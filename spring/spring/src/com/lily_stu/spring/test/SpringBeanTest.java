package com.lily_stu.spring.test;

import com.lily_stu.spring.bean.Car;
import com.lily_stu.spring.bean.Monster;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

public class SpringBeanTest {
    @Test
    public void getMonster() {
        //1.创建/获取容器 ApplicationContext
        //该容器和一个配置文件关联
        //目的：获取该配置文件（bean.xml）里面配置的JavaBean对象（有一个问题：为什么可以读取beans.xml文件
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        //2.获取对象--通过id
        //默认返回的是object，运行类型还是Monster
//        Object monster01 = ioc.getBean("monster01");
        Monster monster01 = (Monster) ioc.getBean("monster01");
        //3.输出
        System.out.println("monster01==>" + monster01);
        System.out.println("monster01.class==>" + monster01.getClass());
        System.out.println("monster01.id==>" + monster01.getId());
        monster01.setId(200);
        System.out.println("ok~");
        //4.k以在获取的时候直接指定bean类型
        Monster monster011 = ioc.getBean("monster01", Monster.class);
        System.out.println("monster011==>" + monster011);
        System.out.println("monster011.id==>" + monster011.getId());

        //monster01和monster011是同一个对象

        //5.查看查看容器注入了哪些bean 对象,输出bean 的id
        String[] names = ioc.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("--" + name);
        }
    }

    //有一个问题：为什么可以读取beans.xml文件
    //获取类加载的路径
    @Test
    public void classPath() {
        File file = new File(this.getClass().getResource("/").getPath());
        System.out.println("file==>" + file);
        //file==>D:\ uidq5850\repo_code\spring\spring\out\production\spring
    }

    @Test
    public void homeWork01() {//bean如果未指定id，就会使用默认id==》全类名+#+序号(从0开始
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Monster bean = (Monster) ioc.getBean("com.lily_stu.spring.bean.Monster#1");
        System.out.println("monster bean==>" + bean);
    }

    @Test
    public void homeWork02() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Car bean = (Car) ioc.getBean("car01");
        System.out.println("car bean==>" + bean);
    }
}
