package com.lilystu.furns.dao.impl;

import com.lilystu.furns.dao.BasicDAO;
import com.lilystu.furns.dao.FurnDAO;
import com.lilystu.furns.entity.Furn;

import java.util.List;

public class FurnDAOImpl extends BasicDAO implements FurnDAO {
    @Override
    public List<Furn> queryFurns() {
        String sql = "SELECT `id`,`name`,`price`,sales`,`stock`,`img_path`" +
                "FROM furn";
        return queryMulti(sql,Furn.class);
    }
}
