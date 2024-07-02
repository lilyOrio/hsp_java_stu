package com.lilystu.springboot.mybatis.dao;

import com.lilystu.springboot.mybatis.bean.Monster;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MonsterMapper {
    Monster getMonsterById(Integer id);
}
