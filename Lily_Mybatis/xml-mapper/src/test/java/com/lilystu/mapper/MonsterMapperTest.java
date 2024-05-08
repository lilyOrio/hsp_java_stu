package com.lilystu.mapper;

import com.lilystu.entity.Monster;
import com.lilystu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MonsterMapperTest {
    private SqlSession sqlSession;
    private MonsterMapper monsterMapper;

    @Before
    public void init(){
        sqlSession = MyBatisUtils.getSqlSession();
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
        System.out.println("MonsterMapper = " + monsterMapper.getClass());
    }

    //6.3.3 parameterType(输入参数类型)
    /**
     * 当有多个查询条件时，传入的参数就是Pojo类型的Java 对象
     */
    @Test
    public void findMonsterByNameORId(){
        Monster monster = new Monster();
        monster.setId(1);
        monster.setName("松鼠精-01");
        List<Monster> monsterList = monsterMapper.findMonsterByNameORId(monster);
        for (Monster m : monsterList) {
            System.out.println("查询到的结果--"+m);
        }
        if (sqlSession != null){
            sqlSession.close();
        }
    }

    /**
     * 模糊查询取值需要用${}
     */
    @Test
    public void findMonsterByName(){
        List<Monster> monsterList = monsterMapper.findMonsterByName("鼠");
        for (Monster m : monsterList) {
            System.out.println("查询到的结果--"+m);
        }
        if (sqlSession != null){
            sqlSession.close();
        }
    }

    /*
        6.3.4 传入HashMap
        1. HashMap 传入参数更加灵活，比如可以灵活的增加查询的属性，而不受限于Monster 这个Pojo 属性本身
        2. 演示如何遍历一个List<Map<String,Object>> 的数据类型
     */
    @Test
    public void findMonsterByIdAndSalary_ParameterHashMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",10);
        map.put("salary",40);
        List<Monster> monsterList = monsterMapper.findMonsterByIdAndSalary_ParameterHashMap(map);
        for (Monster m : monsterList) {
            System.out.println("查询到的结果--"+m);
        }
        if (sqlSession != null){
            sqlSession.close();
        }
    }

    //传入和返回的类型都是map
    @Test
    public void findMonsterByIdAndSalary_ParameterHashMap_ReturnHashMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",10);
        map.put("salary",40);
        List<Map<String, Object>> monsterList = monsterMapper.findMonsterByIdAndSalary_ParameterHashMap_ReturnHashMap(map);
        for (Map<String, Object> monsterMap : monsterList) {
            System.out.println("查询到的结果--"+monsterMap);
            //遍历monsterMap
            Set<String> keys = monsterMap.keySet();
            for (String key : keys) {
                System.out.println(key + "==>" + monsterMap.get(key));
            }
        }
        if (sqlSession != null){
            sqlSession.close();
        }
    }

    //resultMap(结果集映射)
    /*
    当实体类的属性和表的字段名字不一致时，我们可以通过resultMap 进行映射，从而屏蔽
    实体类属性名和表的字段名的不同.
     */
}
