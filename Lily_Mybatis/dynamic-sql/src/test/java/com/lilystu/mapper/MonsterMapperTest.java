package com.lilystu.mapper;

import com.lilystu.entity.Monster;
import com.lilystu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonsterMapperTest {
    //这个是Sql 会话,通过它可以发出sql 语句
    private SqlSession sqlSession;
    private MonsterMapper monsterMapper;

    @Before
    public void init(){
        sqlSession = MyBatisUtils.getSqlSession();
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
        System.out.println("MonsterMapper = " + monsterMapper.getClass());
    }

    @Test
    public void findMonsterByAge(){
        List<Monster> monsterList = monsterMapper.findMonsterByAge(10);
        for (Monster monster : monsterList) {
            System.out.println("monster==>" + monster);
        }
        if (sqlSession != null){
            sqlSession.close();
        }
    }

    @Test
    public void findMonsterByIdAndName(){
        Monster monster = new Monster();
        monster.setId(1);
        monster.setName("狐狸精");
        List<Monster> monsterList = monsterMapper.findMonsterByIdAndName(monster);
        for (Monster m : monsterList) {
            System.out.println("monster==>" + m);
        }
        if (sqlSession != null){
            sqlSession.close();
        }
    }

    /**
     * 1. choose 的标签使用
     * 2. 如果给的name 不为空，就查询名字为输入的妖怪，
     * 如果monster_id>0，就查询monster_id 的妖怪
     * 只会走一个条件，类似switch语句
     */
    @Test
    public void findMonsterByIdAndName_choose(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "白虎精");
        map.put("id", 2);
        List<Monster> monsterList = monsterMapper.findMonsterByIdAndName_choose(map);
        for (Monster m : monsterList) {
            System.out.println("monster==>" + m);
        }
        if (sqlSession != null){
            sqlSession.close();
        }
    }

    @Test
    public void findMonsterById_forEach(){
        Map<String, Object> map = new HashMap<>();
        map.put("ids", Arrays.asList(20, 22, 34));
        List<Monster> monsterList = monsterMapper.findMonsterById_forEach(map);
        for (Monster m : monsterList) {
            System.out.println("monster==>" + m);
        }
        if (sqlSession != null){
            sqlSession.close();
        }
    }

    @Test
    public void findMonsterByName_Trim(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "狐狸精");
        List<Monster> monsterList = monsterMapper.findMonsterByName_Trim(map);
        for (Monster m : monsterList) {
            System.out.println("monster==>" + m);
        }
        if (sqlSession != null){
            sqlSession.close();
        }
    }
}
