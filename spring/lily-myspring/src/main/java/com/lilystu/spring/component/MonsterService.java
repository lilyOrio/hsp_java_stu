package com.lilystu.spring.component;

import com.lilystu.spring.annotation.AutoWire;
import com.lilystu.spring.annotation.Component;
import com.lilystu.spring.processe.InitializingBean;

@Component("monsterService")
public class MonsterService implements InitializingBean{
    @AutoWire
    private MonsterDao monsterDao;

    public void ok(){
        monsterDao.hi();
    }

    @Override
    public void afterPropertiesSet(){
        System.out.println("MonsterService 的初始化方法。。。");
    }
}
