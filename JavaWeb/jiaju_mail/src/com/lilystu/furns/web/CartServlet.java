package com.lilystu.furns.web;

import com.lilystu.furns.entity.Cart;
import com.lilystu.furns.entity.CartItem;
import com.lilystu.furns.entity.Furn;
import com.lilystu.furns.service.FurnsService;
import com.lilystu.furns.service.impl.FurnsServiceImpl;
import com.lilystu.furns.utils.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/cartServlet")
public class CartServlet extends BasicServlet {
    FurnsService furnsService = new FurnsServiceImpl();

    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到添加家具的id
        int id = DataUtils.parseInt(request.getParameter("id"), 0);
        //获取对应id的家具furn
        Furn furn = furnsService.queryFurnById(id);
        if (furn == null) {//未找到对应家具
            //todo:
            return;
        } else {
            //根据furn构建cartItem
            CartItem cartItem =
                    new CartItem(furn.getId(), furn.getName(), furn.getPrice(), 1, furn.getPrice());
            //从session中获取Cart对象
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            if (cart == null) {//当前用户session没有cart
                cart = new Cart();
                request.getSession().setAttribute("cart", cart);
            }
            cart.addItem(cartItem);
            System.out.println("cart = " + cart);

            //返回家具主页
            //request.getHeader("Referer")请求addItem页面（即发起请求添加家具的页面）的url
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
}
