package com.lilystu.furns.service;

import com.lilystu.furns.dao.CartDAO;
import com.lilystu.furns.dao.impl.CartDAOImpl;
import com.lilystu.furns.entity.Cart;
import com.lilystu.furns.entity.CartItem;
import com.lilystu.furns.entity.Page;

public interface CartService {
    Page<CartItem> page(Cart cart,int pageNO , int size);
}
