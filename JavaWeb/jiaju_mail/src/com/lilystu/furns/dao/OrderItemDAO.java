package com.lilystu.furns.dao;

import com.lilystu.furns.entity.OrderItem;

import java.util.List;

/**
 * OrderItemDAO 表示一个订单项
 * @author 韩顺平
 * @version 1.0
 */
public interface OrderItemDAO {
    /**
     * 将传入的orderItem保存到数据表orderItem
     * @param orderItem
     * @return
     */
    public int saveOrderItem(OrderItem orderItem);

    List<OrderItem> getOrderItemById(String id);
}
