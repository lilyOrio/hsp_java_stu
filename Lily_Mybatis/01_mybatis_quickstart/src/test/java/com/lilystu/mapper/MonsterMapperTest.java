package com.lilystu.mapper;

import com.lilystu.entity.Monster;
import com.lilystu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
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
    public void addMonster(){
        for (int i = 0; i < 1; i++) {
            Monster monster = new Monster();
            monster.setAge(100 + i);
            monster.setBirthday(new Date());
            monster.setEmail("tn@sohu.com");
            monster.setGender(1); monster.setName("松鼠精" + i);
            monster.setSalary(9234.89 + i * 10);
            monsterMapper.addMonster(monster);
            System.out.println("刚刚添加的对象的id=" + monster.getId());
        }
        //增删改，需要提交事务
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("保存成功!");
    }

    //测试mybatis 删除功能
    @Test
    public void delMonster() {
        monsterMapper.delMonster(2);
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("删除ok");
    }

    //测试mybatis 的修改
    @Test
    public void updateMonster() {
        Monster monster = new Monster();
        monster.setAge(200);
        monster.setBirthday(new Date());
        monster.setEmail("hspedu@sohu.com");
        monster.setGender(2);
        monster.setName("狐狸精");
        monster.setSalary(9234.89);
        monster.setId(2);
        monsterMapper.updateMonster(monster);
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("修改ok");
    }
    //测查询单个对象
    @Test
    public void getMonsterById() {
        Monster monster = monsterMapper.getMonsterById(2);
        System.out.println(monster);
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
    @Test
    public void findAllMonster() {
        List<Monster> monsterList = monsterMapper.findAllMonster();
        for (Monster monster : monsterList) {
            System.out.println(monster);
        }
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}
