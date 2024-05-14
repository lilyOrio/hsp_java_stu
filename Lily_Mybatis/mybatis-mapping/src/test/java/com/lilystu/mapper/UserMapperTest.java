package com.lilystu.mapper;

import com.lilystu.entity.Person;
import com.lilystu.entity.Pet;
import com.lilystu.entity.User;
import com.lilystu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

public class UserMapperTest {
    //这个是Sql 会话,通过它可以发出sql 语句
    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void init() throws Exception {
        //通过SqlSessionFactory 对象获取一个SqlSession 会话
        sqlSession = MyBatisUtils.getSqlSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void getUserById() {
        User user = userMapper.getUserById(1);
        System.out.println("user：" + user.getId() + "-" + user.getName());
        for (Pet pet : user.getPets()) {
            System.out.println("pet:" + pet.getId() + "-" + pet.getNickname());
            System.out.println("user:" + user.getName());
        }
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}
