package com.lilystu.tomcat.http;

import java.io.OutputStream;

/**
 * 可以封装OutputStream，即可以通过LilyResponse对象返回数据给浏览器
 * 作用等价于原生Servlet的HttpServletResponse
 */
public class LilyResponse {
    private OutputStream outputStream;

    //http的响应头
    public static final String respHeader = "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/html;charset=utf-8\r\n\r\n";

    public LilyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }
}
