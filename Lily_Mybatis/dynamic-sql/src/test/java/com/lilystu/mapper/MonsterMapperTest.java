package com.lilystu.mapper;

import com.lilystu.entity.Monster;
import com.lilystu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
}
