package com.lilystu.mapper;

import com.lilystu.entity.Pet;
import com.lilystu.entity.User;
import com.lilystu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PetMapperTest {
    //这个是Sql 会话,通过它可以发出sql 语句
    private SqlSession sqlSession;
    private PetMapper petMapper;

    @Before
    public void init() throws Exception {
        //通过SqlSessionFactory 对象获取一个SqlSession 会话
        sqlSession = MyBatisUtils.getSqlSession();
        petMapper = sqlSession.getMapper(PetMapper.class);
    }

    @Test
    public void getPetByUserId() {
        List<Pet> pets = petMapper.getPetByUserId(1);
        for (Pet pet : pets) {
            System.out.println("pet:" + pet.getId() + "-" + pet.getNickname());
        }
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    @Test
    public void getPetById() {
        Pet pet = petMapper.getPetById(1);
        System.out.println("pet:" + pet.getId() + "-" + pet.getNickname());
        System.out.println("user:" + pet.getUser().getId() + "-" + pet.getUser().getName());
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}
