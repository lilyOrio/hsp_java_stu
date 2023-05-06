package com.lilystu.tomcat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lily
 * @version 1.0
 * 这是第一个版本的tomcat，可以完成==》接受浏览器请求以及回复消息的功能
 */
public class LilyTomcatV1 {
    public static void main(String[] args) throws IOException {
//        创建监听 在9999端口监听 等待连接请求
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("============服务器在9999端口监听=================");
        while (!serverSocket.isClosed()){
//        如果有连接就创建Socket,这个socket就是浏览器和tomcat的连接通道
            Socket socket = serverSocket.accept();
//        接受浏览器发送的数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mes = null;
            while ((mes = reader.readLine())!=null){
                if (mes.length() == 0){
                    break;
                }
                System.out.println("."+mes);
            }

//            回复消息
//            我们的tomcat 会送-http 响应方式
            OutputStream outputStream = socket.getOutputStream();
            //构建一个http 响应的头
            //\r\n 表示换行
            //http 响应体，需要前面有两个换行\r\n\r\n
            String respHeader = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html;charset=utf-8\r\n\r\n";
            String resp = respHeader + "hi, lilystu";
            System.out.println("========我们的tomcat 给浏览器会送的数据======");
            System.out.println(resp);
            outputStream.write(resp.getBytes());//将resp 字符串以byte[] 方式返回
            outputStream.flush();
            outputStream.close();
            reader.close();
            socket.close();
        }
    }

}
