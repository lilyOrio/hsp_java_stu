package com.lilystu.furns.dao;

import com.lilystu.furns.entity.Furn;

import java.util.List;

public interface FurnDAO {
    //返回家具集合
    List<Furn> queryFurns();
}
