package com.lilystu.furns.dao.impl;

import com.lilystu.furns.dao.BasicDAO;
import com.lilystu.furns.dao.OrderDAO;
import com.lilystu.furns.entity.Order;

import java.util.List;

/**
 * @author lily
 * @version 1.0
 */
public class OrderDAOImpl extends BasicDAO<Order> implements OrderDAO {
    @Override
    public int saveOrder(Order order) {
        String sql = "INSERT INTO `order`(`id`,`create_time`,`price`,`status`,`member_id`) " +
                "VALUES(?,?,?,?,?)";
        return update(sql, order.getId(), order.getCreateTime(),
                order.getPrice(), order.getStatus(), order.getMemberId());
    }

    @Override
    public List<Order> getOrderByMemberId(int memberId) {
        String sql = "SELECT `id`,`create_time`createTime,`price`,`status`,`member_id`memberId FROM " +
                "`ORDER` WHERE `member_id`=?";
        return queryMulti(sql, Order.class, memberId);
    }
}
