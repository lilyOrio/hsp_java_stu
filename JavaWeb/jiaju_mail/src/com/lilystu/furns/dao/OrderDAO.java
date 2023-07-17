package com.lilystu.furns.dao;

import com.lilystu.furns.entity.Order;

import java.util.List;

/**
 * @author lily
 * @version 1.0
 */
public interface OrderDAO {
    int saveOrder(Order order);
    List<Order> getOrderByMemberId(int memberId);
}
