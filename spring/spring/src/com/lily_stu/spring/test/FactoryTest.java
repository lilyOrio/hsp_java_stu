package com.lily_stu.spring.test;

import com.lily_stu.spring.bean.Monster;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lily
 * @version 1.0
 */
public class FactoryTest {
    @Test
    public void getBeanByStaticInstanceFactory() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Monster monster_01 = ioc.getBean("monster_01", Monster.class);
        Monster monster_02 = ioc.getBean("monster_02", Monster.class);
        System.out.println(monster_01);
        System.out.println(monster_02);
        System.out.println(monster_02 == monster_01);//false

        Monster monster_03 = ioc.getBean("monster_03", Monster.class);
        Monster monster_04 = ioc.getBean("monster_04", Monster.class);
        System.out.println(monster_03);
        System.out.println(monster_04);
        System.out.println(monster_03 == monster_04);//true
    }

    @Test
    public void getBeanBy_FactoryBean(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Monster monster_05 = ioc.getBean("monster_05", Monster.class);
        System.out.println(monster_05);
    }
}
