package com.lily_stu.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 自定义异常
 */
@ResponseStatus(reason="年龄需要在1-120 之间",value= HttpStatus.BAD_REQUEST)
public class AgeException extends RuntimeException{
}
