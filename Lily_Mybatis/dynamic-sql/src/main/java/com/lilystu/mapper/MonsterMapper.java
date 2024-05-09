package com.lilystu.mapper;

import com.lilystu.entity.Monster;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MonsterMapper {
    //根据age 查询结果
    public List<Monster>
    findMonsterByAge(@Param("age") Integer age);

    //根据id 和名字来查询结果
    public List<Monster> findMonsterByIdAndName(Monster monster);

    //测试choose 标签的使用
    /*
        需求：如果给的name 不为空，就按名字查询妖怪，如果指定的id>0，就按id 来查询妖怪，
        要求使用choose/when/otherwise 标签实现, 传入参数要求使用Map
     */
    public List<Monster>
    findMonsterByIdAndName_choose(Map<String, Object> map);

    //测试foreach 的标签使用
    public List<Monster>
    findMonsterById_forEach(Map<String, Object> map);

    //trim 标签的使用
    public List<Monster> findMonsterByName_Trim(Map<String, Object> map);

}
