package com.lilystu.furns.dao;

import com.lilystu.furns.entity.Cart;
import com.lilystu.furns.entity.CartItem;
import com.lilystu.furns.entity.Furn;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CartDAO {
    /**
     * 获取当前页显示数据
     * @param pageBegin 从第几条数据开始显示 0开始计算
     * @param pageSize 取出多少条记录
     * @return
     */
    List<CartItem> getPageItem(Cart cart, int pageBegin, int pageSize);

    /**
     * 获取家具数量
     * @return
     */
    int getTotalRow(Cart cart);
}
