package com.lilystu.mapper;

import com.lilystu.entity.Department;
import com.lilystu.entity.Employee;
import com.lilystu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

public class EmployeeMapperTest {
    //这个是Sql 会话,通过它可以发出sql 语句
    private SqlSession sqlSession;
    private EmployeeMapper employeeMapper;

    @Before
    public void init() throws Exception {
    //通过SqlSessionFactory 对象获取一个SqlSession 会话
        sqlSession = MyBatisUtils.getSqlSession();
        employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
    }

    @Test
    public void getIdCardById(){
        Employee employee = employeeMapper.getEmployeeById(1);
        System.out.println(employee);
        if(sqlSession != null) {
            sqlSession.close();
        }
    }
}
