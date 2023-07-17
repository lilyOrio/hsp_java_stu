package com.lilystu.furns.service;

import com.lilystu.furns.entity.Cart;
import com.lilystu.furns.entity.Order;
import com.lilystu.furns.entity.OrderItem;

import java.util.List;

/**
 * @author 韩顺平
 * @version 1.0
 */
public interface OrderService {

    //分析
    //1. 生成订单
    //2. 订单是根据cart来生成, cart对象在session,通过web层，传入saveOrder
    //3. 订单是和一个会员关联
    public String saveOrder(Cart cart, int memberId);

    List<Order> getOrderByMemberId(int memberId);

    List<OrderItem> getOrderItemById(String id);
}
