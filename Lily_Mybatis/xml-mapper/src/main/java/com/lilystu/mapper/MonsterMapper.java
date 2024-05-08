package com.lilystu.mapper;

import com.lilystu.entity.Monster;

import java.util.List;
import java.util.Map;

public interface MonsterMapper {
    //通过id 或者名字查询
    public List<Monster> findMonsterByNameORId(Monster monster);
    //查询名字中含义'精'妖怪 --模糊查询
    public List<Monster> findMonsterByName(String name);
    //查询id > 10 并且salary 大于40, 要求传入的参数是HashMap
    public List<Monster> findMonsterByIdAndSalary_ParameterHashMap(Map<String, Object> map);
    //查询id > 10 并且salary 大于40, 要求传入的参数是HashMap
    public List<Map<String, Object>>
    findMonsterByIdAndSalary_ParameterHashMap_ReturnHashMap(Map<String, Object> map);
}
