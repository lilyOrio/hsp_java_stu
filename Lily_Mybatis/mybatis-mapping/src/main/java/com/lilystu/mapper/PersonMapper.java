package com.lilystu.mapper;

import com.lilystu.entity.Person;

public interface PersonMapper {
    //通过Person 的id 获取到Person,包括这个Person 关联的IdenCard 对象
    public Person getPersonById(Integer id);
}
