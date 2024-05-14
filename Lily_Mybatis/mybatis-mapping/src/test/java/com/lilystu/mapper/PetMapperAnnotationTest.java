package com.lilystu.mapper;

import com.lilystu.entity.Pet;
import com.lilystu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PetMapperAnnotationTest {
    //这个是Sql 会话,通过它可以发出sql 语句
    private SqlSession sqlSession;
    private PetMapperAnnotation petMapperAnnotation;

    @Before
    public void init() throws Exception {
//通过SqlSessionFactory 对象获取一个SqlSession 会话
        sqlSession = MyBatisUtils.getSqlSession();
        petMapperAnnotation = sqlSession.getMapper(PetMapperAnnotation.class);
    }

    //测试getPetByUserId
    @Test
    public void getPetByUserId() {
        List<Pet> pets = petMapperAnnotation.getPetByUserId(1);
        for (Pet pet : pets) {
            System.out.println("pet:" + pet.getNickname());
        }
        System.out.println("================================");
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    //测试getPetById
    @Test
    public void getPetById() {
//1.给一个pet id => 查询到宠物和对应的user
        Pet pet = petMapperAnnotation.getPetById(2);
        System.out.println(pet.getNickname() + " 主人" + pet.getUser().getName());
        System.out.println("===================================================");
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}
