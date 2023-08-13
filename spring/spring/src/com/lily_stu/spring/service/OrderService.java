package com.lily_stu.spring.service;
import com.lily_stu.spring.dao.OrderDao;

/**
 * @author 韩顺平
 * @version 1.0
 * Service类
 */
public class OrderService {
    //OrderDao属性
    private OrderDao orderDao;

    //getter
    public OrderDao getOrderDao() {
        return orderDao;
    }
    //setter
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
