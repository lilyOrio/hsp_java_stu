package com.lilystu.spring.component;

import com.lilystu.spring.annotation.Component;
import com.lilystu.spring.annotation.Scope;

@Component("monsterDao")
@Scope("prototype")
public class MonsterDao {
    public void hi(){
        System.out.println("MonsterDao hi~");
    }
}
