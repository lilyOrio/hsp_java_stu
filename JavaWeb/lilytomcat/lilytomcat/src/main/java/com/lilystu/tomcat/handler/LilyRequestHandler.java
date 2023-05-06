package com.lilystu.tomcat.handler;

import com.lilystu.tomcat.LilyTomcatV3;
import com.lilystu.tomcat.http.LilyRequest;
import com.lilystu.tomcat.http.LilyResponse;
import com.lilystu.tomcat.servlet.LilyCalServlet;
import com.lilystu.tomcat.servlet.LilyHttpServlet;
import com.lilystu.utils.WebUtil;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 处理http请求的线程对象
 */
public class LilyRequestHandler extends Thread {
    Socket socket;

    public LilyRequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
//            BufferedReader bufferedReader = //方便按行读取
//                    new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
//            System.out.println("当前线程："+ Thread.currentThread().getName());
//            System.out.println("============lilyTomcat2.0获取的数据如下=================");
//            String mes = null;
//            while ((mes = bufferedReader.readLine()) != null) {
//                if (mes.length() == 0) {
//                    break;
//                }
//                System.out.println("*" + mes);
//            }
            LilyRequest request = new LilyRequest(socket.getInputStream());
//            String method = request.getMethod();
//            String str1 = request.getParameter("num1");
//            String str2 = request.getParameter("num2");
//            int num1 = WebUtil.paresInt(str1, 0);
//            int num2 = WebUtil.paresInt(str2, 0);
//            System.out.println(num1 + " + " + num2 + " " + "= " + (num1 + num2));
//            System.out.println("method" + "= " + method);
//            System.out.println("uri" + "= " + request.getUri());
            //构建一个http 响应的头
            //\r\n 表示换行
            //http 响应体，需要前面有两个换行\r\n\r\n==>响应头和响应体相隔两个换行
//            String respHeader = "HTTP/1.1 200 OK\r\n" +
//                    "Content-Type: text/html;charset=utf-8\r\n\r\n";
            LilyResponse response = new LilyResponse(socket.getOutputStream());
//            String resp = LilyResponse.respHeader + "hi, lilystu";
//            System.out.println("========lilyTomcat2.0返回的数据======");
//            System.out.println(resp);
//            response.getOutputStream().write(resp.getBytes());//将resp 字符串以byte[] 方式返回
//            outputStream.flush();
//            outputStream.close();
//            inputStream.close();
//            LilyCalServlet servlet = new LilyCalServlet();
//            servlet.service(request,response);
            String uri = request.getUri();
            String servletName = LilyTomcatV3.servletUrlMapping.get(uri);
            LilyHttpServlet servlet = LilyTomcatV3.servletMapping.get(servletName);
            servlet.service(request,response);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
