package lilystu.mapper;


import lilystu.entity.Monster;

import java.util.List;

public interface MonsterMapper {
    //查询-根据id
    public Monster getMonsterById(Integer id);
    //查询所有的Monster
    public List<Monster> findAllMonster();


}
