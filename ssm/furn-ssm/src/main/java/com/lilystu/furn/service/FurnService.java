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

    public void update(Furn furn);

    public void del(Integer id);

    public Furn findById(Integer id);

    public List<Furn> findByCondition(String name);
}
