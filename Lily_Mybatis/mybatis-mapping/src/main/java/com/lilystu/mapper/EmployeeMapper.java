package com.lilystu.mapper;

import com.lilystu.entity.Employee;

public interface EmployeeMapper {
    //通过Person 的id 获取到Person,包括这个Person 关联的IdenCard 对象，方式2
    public Employee getEmployeeById(Integer id);
}
