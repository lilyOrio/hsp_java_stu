package com.lilystu.spring;

import com.lilystu.spring.component.MonsterService;
import com.lilystu.spring.component.SmartAnimalable;
import com.lilystu.spring.ioc.LilySpringApplicationContext;
import com.lilystu.spring.ioc.LilySpringConfig;

public class TestMain {
    public static void main(String[] args) throws Exception {
        LilySpringApplicationContext ioc = new LilySpringApplicationContext(LilySpringConfig.class);

//        //通过spring 容器对象, 获取bean 对象
//        System.out.println(ioc.getBean("mMonsterDao"));
//        System.out.println(ioc.getBean("mMonsterDao"));
//        System.out.println(ioc.getBean("mMonsterDao"));
//
//        System.out.println(ioc.getBean("monsterService"));
//        System.out.println(ioc.getBean("monsterService"));
//        System.out.println(ioc.getBean("monsterService"));
//        System.out.println("Ok~");

        //依赖注入
        MonsterService monsterService = (MonsterService)ioc.getBean("monsterService");
        monsterService.ok();

        //测试AOP
        SmartAnimalable mSmartDog = (SmartAnimalable)ioc.getBean("mSmartDog");
        mSmartDog.getSub(10,20);
        mSmartDog.getSum(100,200);
    }
}
