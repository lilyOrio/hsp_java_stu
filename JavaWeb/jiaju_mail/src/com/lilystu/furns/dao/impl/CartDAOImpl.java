package com.lilystu.furns.dao.impl;

import com.lilystu.furns.dao.CartDAO;
import com.lilystu.furns.entity.Cart;
import com.lilystu.furns.entity.CartItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CartDAOImpl implements CartDAO {
    private List<CartItem> cartItems = new ArrayList<>();

    @Override
    public List<CartItem> getPageItem(Cart cart, int pageBegin, int pageSize) {
        Set<Integer> id = cart.getItems().keySet();
        int order = 0;
        int endItem = pageBegin + pageSize;
        for (Integer itemId : id) {
            order++;
            if (pageBegin <= order && order < endItem){
                cartItems.add(cart.getItems().get(itemId));
            }
        }
        return cartItems;
    }

    @Override
    public int getTotalRow(Cart cart) {
        return cart.getItems().size();
    }


}
