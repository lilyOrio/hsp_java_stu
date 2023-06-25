package com.lilystu.furns.test;

import com.lilystu.furns.dao.FurnDAO;
import com.lilystu.furns.dao.impl.FurnDAOImpl;
import com.lilystu.furns.entity.Furn;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class FurnDAOTest {
    private FurnDAO furnDAO = new FurnDAOImpl();

    @Test
    public void queryFurns() {
        List<Furn> furns = furnDAO.queryFurns();
        System.out.println("家具列表==》" + furns);
    }

    @Test
    public void addFurn() {
        int addFurn = furnDAO.addFurn(new Furn(null, "可爱沙发", "L家具",
                new BigDecimal(999.99), 100, 10,
                "assets/images/product-image/16.jpg"));
        System.out.println("添加家具：" + addFurn);
    }

    @Test
    public void getTotalRow() {
        System.out.println("家具数量：" + furnDAO.getTotalRow());
    }

    @Test
    public void getPageItem() {
        List<Furn> pageItems = furnDAO.getPageItem(3, 3);
        for (Furn furn : pageItems) {
            System.out.println("Furn==>" + furn);
        }
    }

    @Test
    public void getTotalRowByName(){
        System.out.println(furnDAO.getTotalRowByName("沙发"));
    }

    @Test
    public void getPageItemByName(){
        System.out.println(furnDAO.getPageItemByName(0,3,"沙发"));
    }
}
