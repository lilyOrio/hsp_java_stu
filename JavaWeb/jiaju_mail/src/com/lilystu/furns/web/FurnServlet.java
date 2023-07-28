package com.lilystu.furns.web;

import com.lilystu.furns.entity.Furn;
import com.lilystu.furns.entity.Page;
import com.lilystu.furns.service.FurnsService;
import com.lilystu.furns.service.impl.FurnsServiceImpl;
import com.lilystu.furns.utils.DataUtils;
import com.lilystu.furns.utils.WebUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

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
        request.setAttribute("furns", furnList);
        request.getRequestDispatcher("/views/manage/furn_manage.jsp")
                .forward(request, response);
    }

    /**
     * 处理添加家具请求
     *
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
        Furn furn = DataUtils.copyParamToBean(request.getParameterMap(), new Furn());
        System.out.println("add furn = " + furn);
        furnsService.addFurn(furn);
        //添加完毕后，请求转发到家具列表页面，重新走一下list方法
//        request.getRequestDispatcher("/manage/furnServlet?action=list").forward(request,response);
        response.sendRedirect(getServletContext().getContextPath() + "/manage/furnServlet?action=page&pageNo=" + request.getParameter("pageNo"));
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FurnServlet==delete==");
        int id = DataUtils.parseInt(request.getParameter("id"), 0);
        furnsService.deleteFurnById(id);
        response.sendRedirect(getServletContext().getContextPath() + "/manage/furnServlet?action=page&pageNo=" + request.getParameter("pageNo"));
    }

    protected void showFurn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FurnServlet==showFurn==");
        Furn furn = furnsService.queryFurnById(DataUtils.parseInt(request.getParameter("id"), 0));
        request.setAttribute("furn", furn);
        request.getRequestDispatcher("/views/manage/furn_update.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FurnServlet==update==");
        //将提交修改的家居信息，封装成Furn对象

        //如果你的表单是enctype="multipart/form-data", req.getParameter("id") 得不到id
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        //获取到对应furn对象[从db中获取]
        Furn furn = furnsService.queryFurnById(id);
        //todo 做一个判断 furn为空就不处理

        //1. 判断是不是文件表单(enctype="multipart/form-data")
        if (ServletFileUpload.isMultipartContent(req)) {
            //2. 创建 DiskFileItemFactory 对象, 用于构建一个解析上传数据的工具对象
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            //3. 创建一个解析上传数据的工具对象

            ServletFileUpload servletFileUpload =
                    new ServletFileUpload(diskFileItemFactory);
            //解决接收到文件名是中文乱码问题
            servletFileUpload.setHeaderEncoding("utf-8");

            //4. 关键的地方, servletFileUpload 对象可以把表单提交的数据text / 文件
            //   将其封装到 FileItem 文件项中
            try {
                List<FileItem> list = servletFileUpload.parseRequest(req);
                //遍历，并分别处理=> 自然思路
                for (FileItem fileItem : list) {
                    //判断是不是一个文件=> 你是OOP程序员
                    if (fileItem.isFormField()) {//如果是true就是文本 input text(普通的表单字段)

                        if ("name".equals(fileItem.getFieldName())) {//家居名
                            furn.setName(fileItem.getString("utf-8"));
                        } else if ("maker".equals(fileItem.getFieldName())) {//制造商
                            furn.setMaker(fileItem.getString("utf-8"));
                        } else if ("price".equals(fileItem.getFieldName())) {//价格
                            furn.setPrice(new BigDecimal(fileItem.getString()));
                        } else if ("sales".equals(fileItem.getFieldName())) {//销量
                            furn.setSales(new Integer(fileItem.getString()));
                        } else if ("stock".equals(fileItem.getFieldName())) {//库存
                            furn.setStock(new Integer(fileItem.getString()));
                        }

                    } else {//是一个文件

                        //获取上传的文件的名字
                        String name = fileItem.getName();

                        //如果用户没有选择新的图片, name = ""
                        if (!"".equals(name)) {
                            //1.指定一个目录 , 就是我们网站工作目录下
                            String filePath = "/" + WebUtils.FURN_IMG_DIRECTORY;
                            //2. 获取到完整目录 [io/servlet基础]
                            String fileRealPath =
                                    req.getServletContext().getRealPath(filePath);

                            //3. 创建这个上传的目录=> 创建目录?=> Java基础
                            File fileRealPathDirectory = new File(fileRealPath);
                            if (!fileRealPathDirectory.exists()) {//不存在，就创建
                                fileRealPathDirectory.mkdirs();//创建
                            }

                            //4. 将文件拷贝到fileRealPathDirectory目录
                            //   构建一个上传文件的完整路径 ：目录+文件名
                            //   对上传的文件名进行处理, 前面增加一个前缀，保证是唯一即可, 不错
                            name = UUID.randomUUID().toString() + "_" + System.currentTimeMillis() + "_" + name;
                            String fileFullPath = fileRealPathDirectory + "/" + name;
                            fileItem.write(new File(fileFullPath)); //保存

                            fileItem.getOutputStream().close();//关闭流

                            //更新家居的图片路径
                            furn.setImgPath(WebUtils.FURN_IMG_DIRECTORY + "/" + name);
                            //todo 删除原来旧的不用的图片
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("不是文件表单...");
        }

        //更新furn对象->DB
        furnsService.updateFurn(furn);
        //可以请求转发到更新成功的页面..
        req.getRequestDispatcher("/views/manage/update_ok.jsp").forward(req, response);
    }

//    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("FurnServlet==update==");
//        furnsService.updateFurn(DataUtils.copyParamToBean(request.getParameterMap(), new Furn()));
////        response.sendRedirect(getServletContext().getContextPath() + "/manage/furnServlet?action=list");
//        response.sendRedirect(getServletContext().getContextPath() + "/manage/furnServlet?action=page&pageNo="+request.getParameter("pageNo"));
//    }

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FurnServlet==page==");
        System.out.println("pageNo=" + request.getParameter("pageNo"));
        int pageNo = DataUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Furn> page = furnsService.page(pageNo, pageSize);
        //将page对象放入request域中
        request.setAttribute("page", page);
        //请求转发
        request.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(request, response);

    }

}
