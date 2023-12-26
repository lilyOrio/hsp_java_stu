package com.lilystu.lilyspringmvc.servlet;

import com.lilystu.lilyspringmvc.annotation.Controller;
import com.lilystu.lilyspringmvc.annotation.RequestMapping;
import com.lilystu.lilyspringmvc.annotation.RequestParam;
import com.lilystu.lilyspringmvc.context.LilyWebApplicationContext;
import com.lilystu.lilyspringmvc.handler.LilyHandler;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LilyDispatcherServlet extends HttpServlet {
    LilyWebApplicationContext lilyWebApplicationContext = null;
    private ArrayList<LilyHandler> handlerList = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        String configLocation = config.getInitParameter("contextConfigLocation");
        lilyWebApplicationContext = new LilyWebApplicationContext(configLocation);
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
        req.setCharacterEncoding("utf-8");
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
                //通过反射得到的参数数组-> 在反射调用方法时会使用到
                //getParameterTypes 或得到当前这个方法的所有参数信息
                Class<?>[] parameterTypes = handler.getMethod().getParameterTypes();
                Object[] params = new Object[parameterTypes.length];
                for (int i = 0; i < params.length; i++) {
                    if ("HttpServletRequest".equals(parameterTypes[i].getSimpleName())){
                        params[i] = req;
                    }
                    if ("HttpServletResponse".equals(parameterTypes[i].getSimpleName())){
                        params[i] = resp;
                    }
                }
                // 再搞定用户请求时带的参数， 比如http://localhost:8080/monster/list?name=xx&age=10
                Map<String, String[]> parameterMap = req.getParameterMap();
                for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                    //目前我们就考虑取出一个，不考虑checkbox 的一组数据情况
                    String name = entry.getKey();
                    String value = entry.getValue()[0];
                    //这里可先测试一下，给学员看看效果
                    System.out.println("请求参数: " + name + "----" + value);
                    //1. 去得到这个参数在被调用方法的参数的第几个位置(也就是index)
                    //2. 我们专门编写一个方法getRequestParamIndex 来玩
                    int indexRequestParamIndex =
                            getIndexRequestParamIndex(handler.getMethod(), name);
                    if(indexRequestParamIndex != -1){
                        params[indexRequestParamIndex] = value;
                    }else {
                        //如果没有找到, 我们就按照默认的参数名的匹配规则来做
                        List<String> parameterNames = getParameterNames(handler.getMethod());
                        for (int i = 0; i < parameterNames.size(); i++) {
                            if (name.equals(parameterNames.get(i))){
                                params[i] = value;
                                break;
                            }
                        }
                    }
                }
                Object result = handler.getMethod().invoke(handler.getController(), params);
                if (result instanceof String){
                    String viewName = (String)result;
                    if (viewName.contains(":")){
                        String viewType = viewName.split(":")[0];//请求类型
                        String viewPage = viewName.split(":")[1];//请求资源
                        if ("forward".equals(viewType)){
                            req.getRequestDispatcher(viewPage).forward(req,resp);
                        }else if ("redirect".equals(viewType)) {//如果是重定向
                            resp.sendRedirect(viewPage);
                        }
                    }else {//默认请求转发
                        req.getRequestDispatcher(viewName).forward(req,resp);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private int getIndexRequestParamIndex(Method method, String name) {
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (parameter.isAnnotationPresent(RequestParam.class)){
                RequestParam parameterAnnotation = parameter.getAnnotation(RequestParam.class);
                if (parameterAnnotation.value().equals(name)){
                    return i;
                }
            }
        }
        return -1;
    }

    private List<String> getParameterNames(Method method){
        ArrayList<String> list = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            list.add(parameter.getName());
        }
        System.out.println(list);
        return list;
    }
}
