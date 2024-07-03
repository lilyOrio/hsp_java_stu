package com.lilystu.springboot.mybatisplus.dao;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lilystu.springboot.mybatisplus.bean.Monster;
import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface MonsterMapper extends BaseMapper<Monster> {
    //这里可以继续添加需要的方法

    int insertSelective(Monster monster);

    int delByEmail(@Param("email") String email);
}
