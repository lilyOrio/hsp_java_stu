package com.lily_stu.spring.factory;

import com.lily_stu.spring.bean.Monster;
import org.springframework.beans.factory.FactoryBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lily
 * @version 1.0
 */
public class MyFactoryBean implements FactoryBean<Monster> {
    private String key;
    private Map<String, Monster> monsterMapI;

    {
        monsterMapI = new HashMap<>();
        monsterMapI.put("monster01",new Monster(100,"金角大王","宝葫芦"));
        monsterMapI.put("monster02",new Monster(200,"银角大王","宝葫芦"));
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public Monster getObject() throws Exception {
        return monsterMapI.get(key);
    }

    @Override
    public Class<?> getObjectType() {
        return Monster.class;
    }

    @Override
    public boolean isSingleton() {//指定是否返回是单例
        return true;
    }
}
