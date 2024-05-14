package com.lilystu.mapper;

import com.lilystu.entity.Pet;

import java.util.List;

public interface PetMapper {
    //通过User 的id 来获取pet 对象，可能有多个，因此使用List 接收
    public List<Pet> getPetByUserId(Integer userId);
    //通过pet 的id 获取Pet 对象
    public Pet getPetById(Integer id);
}
