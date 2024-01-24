package com.lily_stu.web.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class MyGlobalException {
    //全局异常就不管是哪个Handler 抛出的异常，都可以捕获
    @ExceptionHandler({ClassCastException.class, NumberFormatException.class})
    public String localException(Exception ex, HttpServletRequest request){
        System.out.println("全局异常信息是= " + ex.getMessage());
        request.setAttribute("reason", ex.getMessage());
//如何将异常的信息带到下一个页面.
        return "exception_mes";
    }
}
