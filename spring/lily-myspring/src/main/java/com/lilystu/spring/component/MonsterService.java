package com.lilystu.spring.component;

import com.lilystu.spring.annotation.AutoWire;
import com.lilystu.spring.annotation.Component;

@Component("monsterService")
public class MonsterService {
    @AutoWire
    private MonsterDao monsterDao;

    public void ok(){
        monsterDao.hi();
    }
}
