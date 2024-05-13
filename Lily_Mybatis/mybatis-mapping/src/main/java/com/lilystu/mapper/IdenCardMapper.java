package com.lilystu.mapper;

import com.lilystu.entity.IdenCard;

public interface IdenCardMapper {
    //根据id 获取到身份证
    IdenCard getIdCardById(Integer id);

    IdenCard getIdCardById2(Integer id);

}
