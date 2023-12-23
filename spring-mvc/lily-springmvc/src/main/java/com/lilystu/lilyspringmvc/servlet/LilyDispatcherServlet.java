package com.lilystu.lilyspringmvc.servlet;

import com.lilystu.lilyspringmvc.annotation.Controller;
import com.lilystu.lilyspringmvc.annotation.RequestMapping;
import com.lilystu.lilyspringmvc.context.LilyWebApplicationContext;
import com.lilystu.lilyspringmvc.handler.LilyHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LilyDispatcherServlet extends HttpServlet {
    LilyWebApplicationContext lilyWebApplicationContext = null;
    private ArrayList<LilyHandler> handlerList = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        lilyWebApplicationContext = new LilyWebApplicationContext();
        lilyWebApplicationContext.init();
        initHandlerMapping();
        System.out.println("初始化handlerList = " + handlerList);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        System.out.println("doPost");
        executeDispatch(req,resp);
    }

    /**
     * 1. 完成控制器层url---> Controller ---> 方法的映射关系(该关系封装到HspHandler 对象)
     * 2. 并放入到handlerList 集合中
     * 3. 后面我们可以通过handlerList 结合找到某个url 请求对应的控制器的方法(!!!)
     */
    private void initHandlerMapping() {
        ConcurrentHashMap<String, Object> ioc = lilyWebApplicationContext.ioc;
        if (ioc.isEmpty()) {
            throw new RuntimeException("spring ioc 容器为空");
        } else {
            for (Map.Entry<String, Object> entry : ioc.entrySet()) {
                Class<?> aClass = entry.getValue().getClass();
                if (aClass.isAnnotationPresent(Controller.class)) {
                    Method[] methods = aClass.getDeclaredMethods();
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            RequestMapping requestMappingAnnotation =
                                    method.getAnnotation(RequestMapping.class);
                            String url = requestMappingAnnotation.value();
                            handlerList.add(new LilyHandler(url, entry.getValue(), method));
                        }
                    }
                }
            }
        }
    }

    /**
     * 根据request 请求，返回对应的LilyHandler 对象
     * @param request
     * @return
     */
    private LilyHandler getLilyHandler(HttpServletRequest request){
        //获取的URI 也就是http 请求的URI
        //比如http://localhost:8080/monster/list 的/monster/list 部分
        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);
        for (LilyHandler lilyHandler : handlerList){
            if (requestURI.equals(lilyHandler.getUrl())){
                return lilyHandler;
            }
        }
        return null;
    }

    private void executeDispatch(HttpServletRequest req, HttpServletResponse resp){
        LilyHandler handler = getLilyHandler(req);
        try {
            if (handler == null){
                resp.getWriter().print("<h1>404 NOT FOUND</h1>");
            }else {
                handler.getMethod().invoke(handler.getController(),req,resp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
