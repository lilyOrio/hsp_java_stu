package com.lilystu.furns.test;

import com.lilystu.furns.dao.FurnDAO;
import com.lilystu.furns.dao.impl.FurnDAOImpl;
import com.lilystu.furns.entity.Furn;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FurnDAOTest {
    private FurnDAO furnDAO = new FurnDAOImpl();
    @Test
    public void queryFurns(){
        List<Furn> furns = furnDAO.queryFurns();
        System.out.println("家具列表==》" + furns);
    }
}
