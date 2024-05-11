package com.lilystu.mapper;

import com.lilystu.entity.Person;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface PersonMapperAnnotation {
    @Select("select * from person Where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "card", column = "card_id",
                    one = @One(select = "com.lilystu.mapper.IdenCardMapperAnnotation.getIdenCardById"))})
    public Person getPersonById(Integer id);
}
