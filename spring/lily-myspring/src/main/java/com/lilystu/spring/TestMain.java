package com.lilystu.spring;

import com.lilystu.spring.ioc.LilySpringApplicationContext;
import com.lilystu.spring.ioc.LilySpringConfig;

public class TestMain {
    public static void main(String[] args) throws Exception {
        LilySpringApplicationContext ioc = new LilySpringApplicationContext(LilySpringConfig.class);

        //通过spring 容器对象, 获取bean 对象
        System.out.println(ioc.getBean("mMonsterDao"));
        System.out.println(ioc.getBean("mMonsterDao"));
        System.out.println(ioc.getBean("mMonsterDao"));

        System.out.println(ioc.getBean("monsterService"));
        System.out.println(ioc.getBean("monsterService"));
        System.out.println(ioc.getBean("monsterService"));
        System.out.println("Ok~");
    }
}
