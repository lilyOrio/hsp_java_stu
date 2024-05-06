package com.lilystu.mapper;

import com.lilystu.entity.Monster;
import com.lilystu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * 演示使用原生api操作数据库
 * 仍然需要mapper的xml文件
 */
public class MyBatisNativeTest {
    //这个是Sql 会话,通过它可以发出sql 语句
    private SqlSession sqlSession;

    @Before
    public void init() {
        //返回DefaultSqlSession对象
        sqlSession = MyBatisUtils.getSqlSession();
        System.out.println("sqlSession--" + sqlSession.getClass());
    }

    /**
     * 增删改查
     * 增删改需要提交事物
     */
    @Test
    public void myBatisNativeCrud() {
//=============add start==================
//        Monster monster = new Monster();
//        monster.setAge(200);
//        monster.setBirthday(new Date());
//        monster.setEmail("hspedu100@sohu.com");
//        monster.setGender(2);
//        monster.setName("白骨精");
//        monster.setSalary(9234.89);
//        /*
//            public int insert(String statement, Object parameter) {
//                return this.update(statement, parameter);
//            }
//            statement--方法全类目
//         */
//        int insert = sqlSession.insert(" com.lilystu.mapper.MonsterMapper.addMonster", monster);
//=============add end===================
//===========删除start======================
//        sqlSession.delete("com.lilystu.mapper.MonsterMapper.delMonster", 3);
//===========删除end======================
//===========update start======================
//        Monster monster2 = new Monster();
//        monster2.setAge(300);
//        monster2.setBirthday(new Date());
//        monster2.setEmail("tn100@sohu.com");
//        monster2.setGender(2);
//        monster2.setName("狮驼国妖精");
//        monster2.setSalary(9234.89);
//        monster2.setId(7);
//        sqlSession.update("com.lilystu.mapper.MonsterMapper.updateMonster",
//                monster2);
//===========update end======================
//=========select start=================
        List<Monster> monsterList =
                sqlSession.selectList
                        ("com.lilystu.mapper.MonsterMapper.findAllMonster");
        for (Monster monster : monsterList) {
            System.out.println(monster);
        }
//=========select end=================
        if (sqlSession != null) {
//            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("操作成功!");
    }

}
