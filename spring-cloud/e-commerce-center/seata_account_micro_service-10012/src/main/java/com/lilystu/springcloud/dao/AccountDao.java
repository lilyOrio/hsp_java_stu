package com.lilystu.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountDao {
    void reduce(@Param("userId") Long userId, @Param("money") Integer money);
}
