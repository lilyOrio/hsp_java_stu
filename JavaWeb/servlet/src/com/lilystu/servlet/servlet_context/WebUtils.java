package com.lilystu.servlet.servlet_context;

import javax.servlet.ServletContext;

public class WebUtils {
    public static Integer visitCount(ServletContext servletContext) {
        //从servletContext 获取visit_count 属性k-v
        Object visit_count = servletContext.getAttribute("visit_count");
        //判断visit_count 是否为null
        if (visit_count == null) {//说明是第1 次访问网站
            servletContext.setAttribute("visit_count", 1);
            visit_count = 1;
        } else { //是第二次或以后
        //取出visit_count 属性的值+1
            visit_count = Integer.parseInt(visit_count + "") + 1;
        //放回到servletContext
            servletContext.setAttribute("visit_count", visit_count);
        }
        return Integer.parseInt(visit_count + "");
    }
}
