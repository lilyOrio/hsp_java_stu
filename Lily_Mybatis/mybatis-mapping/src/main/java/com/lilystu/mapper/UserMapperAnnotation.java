package com.lilystu.mapper;

import com.lilystu.entity.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapperAnnotation {
    @Select("select * from mybatis_user where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "pets", column = "id",
                    many = @Many(select = "com.lilystu.mapper.PetMapperAnnotation.getPetByUserId"))
    })
    public User getUserById(Integer id);
}
