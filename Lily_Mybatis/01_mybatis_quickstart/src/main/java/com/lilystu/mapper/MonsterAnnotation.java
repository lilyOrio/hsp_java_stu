package com.lilystu.mapper;

import com.lilystu.entity.Monster;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 注解的方式快速入门
 * 如果是通过注解的方式，可不再使用MonsterMapper.xml
 * 但是需要在mybatis-config.xml 注册含注解的类
 */
public interface MonsterAnnotation {
    //添加方法,将我们的sql 语句直接写在@Insert 注解即可
    //使用注解方式,添加时, 如果要返回自增长id 值, 可以使用@Option 注解, 组合使用
    @Insert("INSERT INTO monster (age,birthday,email,gender,name,salary) "
            + "VALUES(#{age},#{birthday},#{email},#{gender},#{name},#{salary})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public void addMonster(Monster monster);

    //根据id 删除一个Monster
    @Delete("DELETE FROM monster "
            + "WHERE id=#{id}")
    public void delMonster(Integer id);

    //修改Monster
    @Update("UPDATE monster SET age=#{age}, birthday=#{birthday}, "
            + "email = #{email},gender= #{gender}, "
            + "name=#{name}, salary=#{salary} "
            + "WHERE id=#{id}")
    public void updateMonster(Monster monster);

    //查询-根据id
    @Select("SELECT * FROM monster WHERE "
            + "id = #{id}")
    public Monster getMonsterById(Integer id);

    //查询所有的Monster
    @Select("SELECT * FROM monster ")
    public List<Monster> findAllMonster();
}
