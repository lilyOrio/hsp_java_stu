package com.lilystu.furns.web;

import com.lilystu.furns.entity.Furn;
import com.lilystu.furns.service.FurnsService;
import com.lilystu.furns.service.impl.FurnsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

}
