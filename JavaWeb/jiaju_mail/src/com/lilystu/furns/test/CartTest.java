package com.lilystu.furns.test;

import com.lilystu.furns.entity.Cart;
import com.lilystu.furns.entity.CartItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CartTest {
    Cart cart = new Cart();
    @Test
    public void addItem(){
        cart.addItem(new CartItem(1,"沙发",new BigDecimal(10),2,new BigDecimal(20)));
        cart.addItem(new CartItem(1,"沙发",new BigDecimal(10),2,new BigDecimal(20)));
        cart.addItem(new CartItem(2,"小沙发",new BigDecimal(10),2,new BigDecimal(20)));
        System.out.println(cart);
    }
}
