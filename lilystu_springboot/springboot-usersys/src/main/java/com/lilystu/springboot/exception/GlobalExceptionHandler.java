package com.lilystu.springboot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

/**
 * 全局异常处理器
 * 底层由ExceptionHandlerExceptionResolver支持
 */
@Slf4j
@ControllerAdvice //标识一个全局异常处理器
public class GlobalExceptionHandler {

    //处理指定异常,处理异常类型由程序员指定，不指定则处理所有类型的异常
    @ExceptionHandler(value = {ArithmeticException.class, NullPointerException.class,AccessException.class})
    //  Exception e 异常发生后，传递的异常对象
    //  Model model程序员可以提取到的异常信息存放到model中，并在页面中显示
    //  HandlerMethod method 得到异常发生的方法
    public String handlerAriException(Exception e, Model model, HandlerMethod method) {
        log.info("异常信息={}", e.getMessage());
        log.info("异常发生的方法={}", method.getMethod());
        model.addAttribute("msg",e.getMessage());
        return "/error/global";
    }

}
