package com.lilystu.tomcat;

import com.lilystu.tomcat.handler.LilyRequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lily
 * @version 2.0
 * 这是第二个版本的tomcat，使用多线程处理请求
 */
public class LilyTomcatV2 {
    public static void main(String[] args) throws IOException {
        //        创建监听 在9999端口监听 等待连接请求
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("============V2服务器在9999端口监听=================");
        while (!serverSocket.isClosed()){
            Socket socket = serverSocket.accept();
            new Thread(new LilyRequestHandler(socket)).start();
        }
    }
}
