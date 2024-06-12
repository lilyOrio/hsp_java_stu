package com.lilystu.furn.service;

import com.lilystu.furn.bean.Furn;

import java.util.List;

public interface FurnService {
    public void save(Furn furn);

    /**
     * 查询所有家居
     * @return
     */
    public List<Furn> findAll();
}
