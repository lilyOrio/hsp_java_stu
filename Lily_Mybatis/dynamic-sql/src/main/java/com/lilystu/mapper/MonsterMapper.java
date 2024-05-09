package com.lilystu.mapper;

import com.lilystu.entity.Monster;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MonsterMapper {
//根据age 查询结果
    public List<Monster>
    findMonsterByAge(@Param("age") Integer age);
}
