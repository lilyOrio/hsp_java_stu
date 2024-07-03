package com.lilystu.springboot.mybatis.service;

import com.lilystu.springboot.mybatis.bean.Monster;

public interface MonsterService {
    //根据ID返回Monster对象
    Monster getMonsterById(Integer id);
}
