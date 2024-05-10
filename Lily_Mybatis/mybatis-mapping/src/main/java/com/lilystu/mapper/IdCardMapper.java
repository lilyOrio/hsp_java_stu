package com.lilystu.mapper;

import com.lilystu.entity.IdCard;

public interface IdCardMapper {
    //根据id 获取到身份证
    IdCard getIdCardById(Integer id);
}
