package com.lilystu.servlet;

import com.lilystu.utils.WebUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(urlPatterns = "/fileUploadServlet")
public class FileUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //判断是不是一个文件表单 enctype="multipart/form-data"
        if (ServletFileUpload.isMultipartContent(request)) {
            //判断各个表单项是什么类型 type="text"...
//            创建DiskFileItemFactory对象，用于构建一个解析上传数据的工具对象
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
//            创建一个解析上传数据的工具对象
            ServletFileUpload servletFileUpload =
                    new ServletFileUpload(diskFileItemFactory);
            //解决接收到的文件名乱码问题
            servletFileUpload.setHeaderEncoding("utf-8");
//            关键地方！！！servletFileUpload对象可以把表单提交的数据（text/文件）
//            将其封装到FileItem文件项中
            try {
                List<FileItem> list = servletFileUpload.parseRequest(request);
                for (FileItem fileItem : list) {
                    System.out.println("fileItem = " + fileItem);//如果不知道一个对象是什么结构，==》输出或debug
//                List = [name=灏忕害缈?1.jpeg, ==>图片名称
//                StoreLocation=D:\ uidq5850\tomcat\apache-tomcat-8.0.50\temp\ upload_6539e2e8_1886c7861e0__7fcf_00000000.tmp, ==》图片已被浏览器临时保存，这个是保存路径
//                size=169252bytes, isFormField=false, FieldName=pic,
//                name=null,==》文本名称为null
//                StoreLocation=D:\ uidq5850\tomcat\apache-tomcat-8.0.50\temp\ upload_6539e2e8_1886c7861e0__7fcf_00000001.tmp,
//                size=12bytes, isFormField=true, FieldName=name]

//                遍历并分别处理
                    if (fileItem.isFormField()) {//true==>文本  false==>文件
                        String string = fileItem.getString("utf-8");
                        System.out.println(string + "...");
                    } else {//文件
//                     保存文件  把暂存文件保存到指定位置
//                        1.指定一个目录 就是我们网站的工作目录下
                        String filePath = "/upload/";//==>保存到指定目录
//                        2.获取完整路径 ==》指定目录存在的完整路径
                        String fileReadPath = request.getServletContext().getRealPath(filePath);
                        WebUtils.SaveFile(fileItem,fileReadPath);
                        response.setContentType("text/html;charset=utf-8");
                        response.getWriter().write("上传成功~~");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
