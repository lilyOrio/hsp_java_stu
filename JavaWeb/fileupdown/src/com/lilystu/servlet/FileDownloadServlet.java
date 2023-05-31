package com.lilystu.servlet;

import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@WebServlet(urlPatterns = "/fileDownloadServlet")
public class FileDownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      1.准备要下载资源，假定这些文件是公共的资源
//       2.获取文件名
        request.setCharacterEncoding("utf-8");
        String fileName = request.getParameter("name");

//      3.给HTTP响应设置响应头，设置响应头content-type，也就是文件的MIME
//        通过ServletContext来获取
        ServletContext servletContext = request.getServletContext();
        String downPath = "/download/";//从web工程根目录计算
        String downFullPath = downPath + fileName;
        String mimeType = servletContext.getMimeType(downFullPath);//获取文件的mime类型
        System.out.println(mimeType);
        response.setContentType(mimeType);//设置content-type

//        4.给HTTP响应头设置 Content-Disposition
//   这里考虑的细节比较多，比如不同的浏览器写法不一样，考虑编码
        //   ff 是 文件名中文需要 base64, 而 ie/chrome 是 URL编码
        //   这里我们不需要同学们记住，只需知道原理
        //   老韩解读
        //(1)如果是Firefox 则中文编码需要 base64
        //(2)Content-Disposition 是指定下载的数据的展示形式 , 如果attachment 则使用文件下载方式
        //(3)如果是其他(主流ie/chrome) 中文编码使用URL编码
        if (request.getHeader("User-Agent").contains("Firefox")) {
            // 火狐 Base64编码
            response.setHeader("Content-Disposition", "attachment; filename==?UTF-8?B?" +
                    new BASE64Encoder().encode(fileName.getBytes("UTF-8")) + "?=");
        } else {
            // 其他(主流ie/chrome)使用URL编码操作
            response.setHeader("Content-Disposition", "attachment; filename=" +
                    URLEncoder.encode(fileName, "UTF-8"));
        }
        //5. 读取下载的文件数据，返回给客户端/浏览器
        //(1) 创建一个和要下载的文件，关联的输入流
        InputStream resourceAsStream =
                servletContext.getResourceAsStream(downFullPath);
        //(2) 得到返回数据的输出流 [因为返回文件大多数是二进制(字节), IO java基础]
        ServletOutputStream outputStream = response.getOutputStream();

        //(3) 使用工具类，将输入流关联的文件，对拷到输出流，并返回给客户端/浏览器
        IOUtils.copy(resourceAsStream, outputStream);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
