package com.lilystu.mapper;

import com.lilystu.entity.Pet;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PetMapperAnnotation {
    //通过User 的id 来获取pet 对象，可能有多个，因此使用List 接收
    @Select("select * from mybatis_pet where user_id=#{userId}")
    @Results(id = "PetResultMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "user", column = "user_id",
                    one = @One(select = "com.lilystu.mapper.UserMapperAnnotation.getUserById"))
    })
    public List<Pet> getPetByUserId(Integer userId);


//    @Select("select id AS tnId,nickname AS tnNickname, user_id AS tnUser_id from\n" +
//            "mybatis_pet where id=#{id}")
//    @Results({
//            @Result(id = true, property = "id", column = "tnId"),
//            @Result(property = "nickname", column = "tnNickname"),
//            @Result(property = "user", column = "tnUser_id",
//                    one = @One(select = "com.lilystu.mapper.UserMapperAnnotation.getUserById"))
//    })

    @Select("select * from mybatis_pet where id=#{Id}")
    @ResultMap("PetResultMap")
    public Pet getPetById(Integer id);
}
