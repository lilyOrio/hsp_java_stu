package com.lilystu.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @author lily
 * @version 1.0
 */
public class LilyServletRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
       System.out.println("LilyServletRequestListener 监听到销毁request");
        }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        System.out.println("访问IP = " + servletRequest.getRemoteAddr() );
        System.out.println("访问资源 = " + ((HttpServletRequest)servletRequest).getRequestURL() );
    }
}
