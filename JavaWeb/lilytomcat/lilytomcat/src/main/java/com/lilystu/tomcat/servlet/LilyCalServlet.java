package com.lilystu.tomcat.servlet;

import com.lilystu.tomcat.http.LilyRequest;
import com.lilystu.tomcat.http.LilyResponse;
import com.lilystu.utils.WebUtil;

import java.io.IOException;
import java.io.OutputStream;

public class LilyCalServlet extends LilyHttpServlet {
    @Override
    public void doGet(LilyRequest request, LilyResponse response) throws IOException {
        String str1 = request.getParameter("num1");
        String str2 = request.getParameter("num2");
        int num1 = WebUtil.paresInt(str1, 0);
        int num2 = WebUtil.paresInt(str2, 0);
        OutputStream outputStream = response.getOutputStream();
        String resp = LilyResponse.respHeader
                + "<h1>" + num1 + " + " + num2 + " " + "= " + (num1 + num2) + " LilyTomcatV3</h1>";
        outputStream.write(resp.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    @Override
    public void doPost(LilyRequest request, LilyResponse response) throws IOException {
        doGet(request, response);
    }

    @Override
    public void init() throws Exception {

    }
}
