package com.lilystu.springboot.mybatis.service.impl;

import com.lilystu.springboot.mybatis.bean.Monster;
import com.lilystu.springboot.mybatis.dao.MonsterMapper;
import com.lilystu.springboot.mybatis.service.MonsterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MonsterServiceImpl implements MonsterService {

    @Resource
    private MonsterMapper monsterMapper;

    @Override
    public Monster getMonsterById(Integer id) {
        return monsterMapper.getMonsterById(id);
    }
}
