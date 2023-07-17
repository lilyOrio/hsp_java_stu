package com.lilystu.furns.web;

import com.lilystu.furns.entity.Cart;
import com.lilystu.furns.entity.Member;
import com.lilystu.furns.entity.Order;
import com.lilystu.furns.entity.OrderItem;
import com.lilystu.furns.service.OrderService;
import com.lilystu.furns.service.impl.OrderServiceImpl;
import com.lilystu.furns.utils.JDBCUtilsByDruid;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author lily
 * @version 1.0
 */
@WebServlet(urlPatterns = "/orderServlet")
public class OrderServlet extends BasicServlet {
    OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void saveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("OrderServlet==saveOrder==");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (null == cart || cart.isEmpty()) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        Member member = (Member) request.getSession().getAttribute("member");
        if (null == member) {//用户未登录
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
            return;
        }
        Integer id = member.getId();
        //如果只希望对这个方法orderService.saveOrder进行事务控制，可以不是要过滤器，直接在这里进行提交或回滚
//        String orderId = null;
//        try {
//            orderId = orderService.saveOrder(cart, id);
//            JDBCUtilsByDruid.commit();
//        } catch (Exception e) {
//            JDBCUtilsByDruid.rollback();
//            e.printStackTrace();
//        }

        String orderId = orderService.saveOrder(cart, id);
        request.getSession().setAttribute("orderId", orderId);

        response.sendRedirect(request.getContextPath() + "/views/order/checkout.jsp");


    }

    protected void manageOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("OrderServlet==manageOrder==");
        Member member = (Member) request.getSession().getAttribute("member");
        if (member == null) {
            //todo
            return;
        }
        List<Order> orderByMemberId = orderService.getOrderByMemberId(member.getId());
        request.setAttribute("orderByMemberId", orderByMemberId);
        request.getRequestDispatcher("/views/cart/order.jsp")
                .forward(request, response);
    }

    protected void orderItemList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("OrderServlet==orderItemList==");
        String orderId = request.getParameter("orderId");
        List<OrderItem> orderItemById = orderService.getOrderItemById(orderId);
        request.setAttribute("orderItemById", orderItemById);
        request.setAttribute("orderId", orderId);
        request.getRequestDispatcher("/views/cart/order_detail.jsp")
                .forward(request, response);
    }

}
