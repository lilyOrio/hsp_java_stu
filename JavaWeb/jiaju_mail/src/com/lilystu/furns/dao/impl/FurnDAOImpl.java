package com.lilystu.furns.dao.impl;

import com.lilystu.furns.dao.BasicDAO;
import com.lilystu.furns.dao.FurnDAO;
import com.lilystu.furns.entity.Furn;

import java.util.List;

public class FurnDAOImpl extends BasicDAO implements FurnDAO {
    @Override
    public List<Furn> queryFurns() {
        String sql = "SELECT `id`, `NAME` , `maker`, `price`, `sales`, `stock`, `img_path` imgPath FROM furn";
        return queryMulti(sql, Furn.class);
    }

    @Override
    public int addFurn(Furn furn) {
        String sql = "INSERT INTO furn(`id` , `name` , `maker` , `price` , `sales` , `stock` , `img_path`)\n" +
                "VALUES(NULL , ? , ? , ? , ? , ? ,?";
        return update(sql, furn.getName(), furn.getMaker(), furn.getPrice(), furn.getSales(), furn.getStock()
                , furn.getImgPath());
    }

    @Override
    public int deleteFurnById(int id) {
        String sql = "DELETE FROM `furn` WHERE `id`=?";
        return update(sql, id);
    }
}
