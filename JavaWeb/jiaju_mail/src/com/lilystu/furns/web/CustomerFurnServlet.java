package com.lilystu.furns.web;

import com.lilystu.furns.entity.Page;
import com.lilystu.furns.service.FurnsService;
import com.lilystu.furns.service.impl.FurnsServiceImpl;
import com.lilystu.furns.utils.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lily
 * @version 1.0
 */
@WebServlet(urlPatterns = "/customerFurnServlet")
public class CustomerFurnServlet extends BasicServlet {
    private FurnsService furnsService = new FurnsServiceImpl();

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CustomerFurnServlet==page==");
        int pageNo = DataUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        Page page = furnsService.page(pageNo, pageSize);
        page.setUrl("customerFurnServlet?action=page");
        //将page对象放入request域中
        request.setAttribute("page", page);
        //请求转发
        request.getRequestDispatcher("/views/customer/index.jsp").forward(request, response);

    }

    protected void pageByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CustomerFurnServlet==pageByName==");
        int pageNo = DataUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        String name = request.getParameter("name");
        if (null == name) {
            name = "";
        }
        Page page = furnsService.pageByName(pageNo, pageSize, name);
        StringBuilder url = new StringBuilder("customerFurnServlet?action=pageByName");
        if (!"".equals(name)){
            url.append("&name=").append(name);
        }
        page.setUrl(url.toString());
        //将page对象放入request域中
        request.setAttribute("page", page);
        //请求转发
        request.getRequestDispatcher("/views/customer/index.jsp").forward(request, response);

    }

}
