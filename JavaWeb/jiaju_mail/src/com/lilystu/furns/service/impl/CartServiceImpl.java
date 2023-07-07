package com.lilystu.furns.service.impl;

import com.lilystu.furns.dao.CartDAO;
import com.lilystu.furns.dao.impl.CartDAOImpl;
import com.lilystu.furns.entity.Cart;
import com.lilystu.furns.entity.CartItem;
import com.lilystu.furns.entity.Page;
import com.lilystu.furns.service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    CartDAO cartDAO = new CartDAOImpl();

    @Override
    public Page<CartItem> page(Cart cart, int pageNO, int size) {
//         //先创建一个page对象再一个一个填充数据
//        Page<Furn> furnPage = new Page<>();
//        furnPage.setPageNo(pageNO);
//        furnPage.setPageSize(pageSize);
//
//        int totalRow = furnDAO.getTotalRow();
//        furnPage.setTotalRow(totalRow);
//
//        //一共有多少页，涉及一个小小的算法
//        int pageTotalCount = totalRow / pageSize;
//        if (totalRow % pageSize > 0) {
//            pageTotalCount += 1;
//        }
//        furnPage.setPageTotalCount(pageTotalCount);
//
//        //每页显示的数据
//        int begin = (pageNO - 1) * pageSize;
//        List<Furn> pageItem = furnDAO.getPageItem(begin, pageSize);
//        furnPage.setItems(pageItem);
        //先创建一个page对象再一个一个填充数据
        Page<CartItem> cartItemPage = new Page<>();
        cartItemPage.setPageNo(pageNO);
        cartItemPage.setPageSize(size);

        int totalRow = cartDAO.getTotalRow(cart);
        cartItemPage.setTotalRow(totalRow);
        //一共有多少页，涉及一个小小的算法
        int pageTotalCount = totalRow / size;
        if (totalRow % size > 0) {
            pageTotalCount += 1;
        }
        cartItemPage.setPageTotalCount(pageTotalCount);

        //每页显示的数据
        int begin = (pageNO - 1) * size + 1;
        List<CartItem> pageItem = cartDAO.getPageItem(cart,begin, size);
        cartItemPage.setItems(pageItem);

        return cartItemPage;
    }
}
