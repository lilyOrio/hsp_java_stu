package com.lilystu.furns.dao.impl;

import com.lilystu.furns.dao.BasicDAO;
import com.lilystu.furns.dao.OrderItemDAO;
import com.lilystu.furns.entity.OrderItem;

import java.util.List;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class OrderItemDAOImpl extends BasicDAO<OrderItem> implements OrderItemDAO {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        //写代码一定要小心1min，不要急
        String sql = "INSERT INTO `order_item`(`id`,`name`,`price`,`count`,`total_price`,`order_id`) " +
                "VALUES(?, ?,?,?,?,?) ";//sqlyoy
        return update(sql,orderItem.getId(),orderItem.getName(),orderItem.getPrice(),
                orderItem.getCount(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> getOrderItemById(String orderId) {
        String sql = "Select `id`,`name`,`price`,`count`,`total_price`totalPrice,`order_id`orderId " +
                "From `ORDER_item` where `order_id`=?";
        return queryMulti(sql,OrderItem.class,orderId);
    }
}
