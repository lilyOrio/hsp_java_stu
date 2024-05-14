package com.lilystu.mapper;

import com.lilystu.entity.Pet;
import com.lilystu.entity.User;
import com.lilystu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserMapperAnnotationTest {
    //这个是Sql 会话,通过它可以发出sql 语句
    private SqlSession sqlSession;
    private UserMapperAnnotation userMapperAnnotation;

    @Before
    public void init() throws Exception {
//通过SqlSessionFactory 对象获取一个SqlSession 会话
        sqlSession = MyBatisUtils.getSqlSession();
        userMapperAnnotation = sqlSession.getMapper(UserMapperAnnotation.class);
    }

    @Test
    public void getUserById() {
//2. 有user id =》查询主人的信息和养了哪些宠物
        User user = userMapperAnnotation.getUserById(2);
        System.out.println("主人是" + user.getName());
//取出宠物
        List<Pet> pets = user.getPets();
        for (Pet pet : pets) {
            System.out.println(pet.getNickname());
        }
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}
