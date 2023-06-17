package com.lilystu.furns.service.impl;

import com.lilystu.furns.dao.FurnDAO;
import com.lilystu.furns.dao.impl.FurnDAOImpl;
import com.lilystu.furns.entity.Furn;
import com.lilystu.furns.service.FurnsService;

import java.util.List;

public class FurnsServiceImpl implements FurnsService {
    private FurnDAO furnDAO = new FurnDAOImpl();

    @Override
    public List<Furn> queryFurns() {
        return furnDAO.queryFurns();
    }
}
