package com.lily_stu.web.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 局部异常处理
 */
@Controller
public class MyExceptionHandler {
    @ExceptionHandler({ArithmeticException.class,NullPointerException.class})
    public String localException(Exception ex, HttpServletRequest request){
        System.out.println("异常信息捕获==》" + ex.getMessage());
        request.setAttribute("reason",ex.getMessage());
        return "exception_mes";
    }

    @RequestMapping(value="/testException01")
    public String test01(Integer num){
        int i = 9/num;
        return "success";
    }
    @RequestMapping(value="/testGlobalException")
    public String global(){
    //1. 模拟NumberFormatException
    //2. 在定义的局部异常中，没有对NumberFormatException
    //3. 所有就会去找全局异常处理
        int num = Integer.parseInt("hello");
        return "success";
    }

    //抛出自定义异常
    @RequestMapping(value="/testException02")
    public String test02(){
        throw new AgeException();
    }

    //统一的异常
    @RequestMapping(value="/testException03")
    public String test03(){
        int[] arr = new int[]{3,9,10,190};
        System.out.println(arr[90]);
        return "success";
    }

    //如果发生了没有归类的异常， 可以给出统一提示页面
    @RequestMapping(value="/testException04")
    public String test04(){
        String str = "hello";
        char c = str.charAt(10);
        return "success";
    }
}
