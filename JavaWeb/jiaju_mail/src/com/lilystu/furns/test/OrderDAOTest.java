package com.lilystu.furns.test;


import com.lilystu.furns.dao.OrderDAO;
import com.lilystu.furns.dao.impl.OrderDAOImpl;
import com.lilystu.furns.entity.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class OrderDAOTest {

    private OrderDAO orderDAO = new OrderDAOImpl();
    @Test
    public void saveOrder() {
        Order order =
                new Order("sn00002", new Date(), new BigDecimal(200), 0, 2);
        System.out.println(orderDAO.saveOrder(order));
    }

    @Test
    public void getOrderByMemberId(){
        List<Order> orderList = orderDAO.getOrderByMemberId(6);
        System.out.println(orderList);
    }
}
