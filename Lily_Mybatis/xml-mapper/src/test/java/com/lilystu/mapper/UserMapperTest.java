package com.lilystu.mapper;

import com.lilystu.entity.User;
import com.lilystu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserMapperTest {
    private SqlSession sqlSession;
    private UserMapper userMapper;

    @Before
    public void init(){
        sqlSession = MyBatisUtils.getSqlSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
        System.out.println("MonsterMapper = " + userMapper.getClass());
    }

    @Test
    public void addUser(){
        User user = new User();
        user.setUserEmail("lily@qq.com");
        user.setUserName("lily");
        userMapper.addUser(user);
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("操作成功");
    }

    @Test
    public void findAllUser(){
        List<User> users = userMapper.findAllUser();
        for (User user : users) {
            System.out.println("user--" + user);
        }
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("操作成功");
    }
}
