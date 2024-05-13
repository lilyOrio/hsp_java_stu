package com.lilystu.mapper;

import com.lilystu.entity.Department;
import com.lilystu.entity.IdenCard;
import com.lilystu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

public class DepartmentMapperTest {
    //这个是Sql 会话,通过它可以发出sql 语句
    private SqlSession sqlSession;
    private DepartmentMapper departmentMapper;

    @Before
    public void init() throws Exception {
    //通过SqlSessionFactory 对象获取一个SqlSession 会话
        sqlSession = MyBatisUtils.getSqlSession();
        departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
    }

    @Test
    public void getIdCardById(){
        Department department = departmentMapper.findDeptById(1);
        System.out.println(department);
        if(sqlSession != null) {
            sqlSession.close();
        }
    }
}
