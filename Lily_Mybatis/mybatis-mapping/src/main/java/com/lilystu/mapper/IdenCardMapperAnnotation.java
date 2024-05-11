package com.lilystu.mapper;

import com.lilystu.entity.IdenCard;
import org.apache.ibatis.annotations.Select;

public interface IdenCardMapperAnnotation {
    //根据id 获取到身份证
//这个方法不需要返回任何级联对象
    @Select("SELECT * FROM idencard WHERE id=#{id}")
    public IdenCard getIdenCardById(Integer id);
}
