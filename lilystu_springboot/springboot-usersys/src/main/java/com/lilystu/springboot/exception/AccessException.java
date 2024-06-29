package com.lilystu.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 自定义异常类
 */
//value = HttpStatus.FORBIDDEN 指定发生此异常返回的状态码
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AccessException extends RuntimeException{

    //用于指定异常信息
    public AccessException(String errMsg){
        super(errMsg);
    }

    public AccessException(){}
    /*
    发生异常后
    1.会先去判断有没有方法去处理这个异常--按异常类型来
        ExceptionHandlerExceptionResolver.java
        ServletInvocableHandlerMethod exceptionHandlerMethod = this.getExceptionHandlerMethod(handlerMethod, exception);
    2.如果没找到就使用默认异常处理机制，--按返回的状态码来
        DefaultErrorViewResolver
     */
}
