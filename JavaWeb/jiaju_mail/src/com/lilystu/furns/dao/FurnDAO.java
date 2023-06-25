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

    /**
     * 根据id查询家具信息
     * @param id
     * @return
     */
    Furn queryFurnById(int id);

    /**
     * 更新家具信息
     * @param furn
     * @return
     */
    int updateFurn(Furn furn);

    /**
     * 获取家具数量
     * @return
     */
    int getTotalRow();

    /**
     * 获取当前页显示数据
     * @param pageBegin 从第几条数据开始显示 0开始计算
     * @param pageSize 取出多少条记录
     * @return
     */
    List<Furn> getPageItem(int pageBegin,int pageSize);

    /**
     * 根据名字获取总家具数量
     * @return
     */
    int getTotalRowByName(String name);

    /**
     * 获取当前页显示数据
     * @param pageBegin 从第几条数据开始显示 0开始计算
     * @param pageSize 取出多少条记录
     * @param name 家具名称
     * @return
     */
    List<Furn> getPageItemByName(int pageBegin,int pageSize,String name);
}
