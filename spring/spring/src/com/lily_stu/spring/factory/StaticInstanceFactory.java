package com.lily_stu.spring.factory;

import com.lily_stu.spring.bean.Monster;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lily
 * @version 1.0
 */
public class StaticInstanceFactory {
    private Map<String, Monster> monsterMapI;
    private static Map<String, Monster> monsterMapS;

    {
        monsterMapI = new HashMap<>();
        monsterMapI.put("monster01",new Monster(100,"金角大王","宝葫芦"));
        monsterMapI.put("monster02",new Monster(200,"银角大王","宝葫芦"));
    }

    static {
        monsterMapS = new HashMap<>();
        monsterMapS.put("monster03",new Monster(300,"金角大王~","宝葫芦~"));
        monsterMapS.put("monster04",new Monster(400,"银角大王~","宝葫芦~"));
    }

    public Monster getMonsterMapI(String key) {
        return monsterMapI.get(key);
    }

    public static Monster getMonsterMapS(String key) {
        return monsterMapS.get(key);
    }

}
