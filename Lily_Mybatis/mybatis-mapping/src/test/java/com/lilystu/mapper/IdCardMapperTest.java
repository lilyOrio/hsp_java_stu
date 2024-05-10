package com.lilystu.mapper;

import com.lilystu.entity.IdCard;
import com.lilystu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

public class IdCardMapperTest {
    //这个是Sql 会话,通过它可以发出sql 语句
    private SqlSession sqlSession;
    private IdCardMapper idCardMapper;

    @Before
    public void init() throws Exception {
    //通过SqlSessionFactory 对象获取一个SqlSession 会话
        sqlSession = MyBatisUtils.getSqlSession();
        idCardMapper = sqlSession.getMapper(IdCardMapper.class);
    }

    //测试是否可以获取身份证
    @Test
    public void getIdCardById(){
        IdCard card = idCardMapper.getIdCardById(1);
        System.out.println(card);
        if(sqlSession != null) {
            sqlSession.close();
        }
    }
}
