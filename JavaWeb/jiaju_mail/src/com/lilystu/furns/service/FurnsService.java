package com.lilystu.furns.service;

import com.lilystu.furns.entity.Furn;
import com.lilystu.furns.entity.Page;

import java.util.List;

public interface FurnsService {
    List<Furn> queryFurns();
    int addFurn(Furn furn);
    int deleteFurnById(int id);
    Furn queryFurnById(int id);
    int updateFurn(Furn furn);

    /**
     * 返回page对象
     * @param pageNO 显示第几页
     * @param size 每页显示多少条记录
     * @return
     */
    Page<Furn> page(int pageNO ,int size);

    Page<Furn> pageByName(int pageNO ,int size,String name);

}
