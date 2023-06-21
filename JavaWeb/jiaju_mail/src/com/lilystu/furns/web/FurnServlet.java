package com.lilystu.furns.web;

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
import java.math.BigDecimal;
import java.util.List;

//小技巧，设置idea标签页
@WebServlet(urlPatterns = "/manage/furnServlet")
public class FurnServlet extends BasicServlet {
    private FurnsService furnsService = new FurnsServiceImpl();

    /**
     * 使用模板模式+动态绑定+反射 调用List方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FurnServlet==List==");
        List<Furn> furnList = furnsService.queryFurns();
        for (Furn f : furnList) {
            System.out.println(f);
        }

        //将集合放入到request 域中
        request.setAttribute("furns",furnList);
        request.getRequestDispatcher("/views/manage/furn_manage.jsp")
                .forward(request,response);
    }

    /**
     * 处理添加家具请求
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FurnServlet==add==");
//        String name = request.getParameter("name");
//        String maker = request.getParameter("maker");
//        String price = request.getParameter("price");
//        String sales = request.getParameter("sales");
//        String stock = request.getParameter("stock");
//        //默认图片路径
//
//        Furn furn = new Furn(null, name, maker, DataUtils.parseBigDecimal(price,new BigDecimal("0.00")), DataUtils.parseInt(sales,0),
//                DataUtils.parseInt(stock,0), "assets/images/product-image/16.jpg");
        furnsService.addFurn(DataUtils.copyParamToBean(request.getParameterMap(),new Furn()));
        //添加完毕后，请求转发到家具列表页面，重新走一下list方法
//        request.getRequestDispatcher("/manage/furnServlet?action=list").forward(request,response);
        response.sendRedirect(getServletContext().getContextPath()+"/manage/furnServlet?action=list");
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FurnServlet==delete==");
        int id = DataUtils.parseInt(request.getParameter("id"),0);
        furnsService.deleteFurnById(id);
        response.sendRedirect(getServletContext().getContextPath()+"/manage/furnServlet?action=list");
    }

}
