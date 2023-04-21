import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 这是我们自己写的一个web 服务, 可以返回hello.html 给浏览器
 */
public class MyWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);//监听9999端口
        //如果serverSocket 没有关闭，就等待连接, 不停的等待
        while (!serverSocket.isClosed()) {
            System.out.println("=====我的web 服务在9999 端口监听=====");
            // 等待浏览器/客户端连接, 得到socket
            // 该socket 用于通信
            Socket socket = serverSocket.accept();//等待客户端连接

//            通过socket 得到输出流
            OutputStream outputStream = socket.getOutputStream();

            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/hello.html"));

            String buf = "";
            while ((buf = bufferedReader.readLine())!=null){
                outputStream.write(buf.getBytes());
            }

            outputStream.close();
            socket.close();
        }
        serverSocket.close();
    }
}
