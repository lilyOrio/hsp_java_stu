package com.lilystu.mapper;

import com.lilystu.entity.IdenCard;
import com.lilystu.entity.Person;
import com.lilystu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

public class PersonMapperAnnotationTest {
    //这个是Sql 会话,通过它可以发出sql 语句
    private SqlSession sqlSession;
    private PersonMapperAnnotation personMapperAnnotation;

    @Before
    public void init() throws Exception {
        //通过SqlSessionFactory 对象获取一个SqlSession 会话
        sqlSession = MyBatisUtils.getSqlSession();
        personMapperAnnotation = sqlSession.getMapper(PersonMapperAnnotation.class);
    }

    //测试是否可以获取身份证
    @Test
    public void getIdCardById(){
        Person person = personMapperAnnotation.getPersonById(1);
        System.out.println(person);
        if(sqlSession != null) {
            sqlSession.close();
        }
    }
}