package com.lilystu.mapper;

import com.lilystu.entity.Monster;

import java.util.List;

public interface MonsterMapper {
    //添加方法
    public void addMonster(Monster monster);
    //根据id 删除一个Monster
    public void delMonster(Integer id);
    //修改Monster
    public void updateMonster(Monster monster);
    //查询-根据id
    public Monster getMonsterById(Integer id);
    //查询所有的Monster
    public List<Monster> findAllMonster();


}
