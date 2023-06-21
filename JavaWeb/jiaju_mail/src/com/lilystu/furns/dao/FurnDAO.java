package com.lilystu.furns.dao;

import com.lilystu.furns.entity.Furn;

import java.util.List;

public interface FurnDAO {
    /**
     * 查询所有家具
     * @return
     */
    List<Furn> queryFurns();

    /**
     * 添加家具
     * @param furn
     * @return
     */
    int addFurn(Furn furn);

    /**
     * 根据id删除家具
     * @param id
     * @return
     */
    int deleteFurnById(int id);
}
