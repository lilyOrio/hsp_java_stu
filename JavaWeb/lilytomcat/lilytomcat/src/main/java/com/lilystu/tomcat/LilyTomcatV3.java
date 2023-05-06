package com.lilystu.tomcat;

import com.lilystu.tomcat.handler.LilyRequestHandler;
import com.lilystu.tomcat.servlet.LilyHttpServlet;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lily
 * @version 2.0
 * 这是第三个版本的tomcat，实现通过xml文件+反射来初始化容器
 */
public class LilyTomcatV3 {
    //1. 存放容器 servletMapping
    // -ConcurrentHashMap
    // -HashMap
    // key            - value
    // ServletName    对应的实例
    public static final ConcurrentHashMap<String, LilyHttpServlet>
            servletMapping = new ConcurrentHashMap<>();

    //2容器 servletUrlMapping url映射
    // -ConcurrentHashMap
    // -HashMap
    // key                    - value
    // url-pattern       ServletName
    public static final ConcurrentHashMap<String, String>
            servletUrlMapping = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        LilyTomcatV3 lilyTomcatV3 = new LilyTomcatV3();
        lilyTomcatV3.init();
        run();
    }

    //启动容器
    public static void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("============V2服务器在9999端口监听=================");
        while (!serverSocket.isClosed()){
            Socket socket = serverSocket.accept();
            new Thread(new LilyRequestHandler(socket)).start();
        }
    }

    //初始化容器
    public void init() {
        //读取web.xml => dom4j =>
        //得到web.xml文件的路径 => 拷贝一份.
        String path = LilyTomcatV3.class.getResource("/").getPath();
//        System.out.println("path= " + path);
// path= /D:/uidq5850/repo_code/JavaWeb/lilytomcat/lilytomcat/target/classes/
        //使用dom4j技术完成读取
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new File(path + "web.xml"));
            System.out.println("document= " + document);
            //得到根元素
            Element rootElement = document.getRootElement();
            //得到根元素下面的所有元素
            List<Element> elements = rootElement.elements();
            //遍历并过滤到 servlet servlet-mapping
            for (Element element : elements) {
                if ("servlet".equalsIgnoreCase(element.getName())) {
                    //这是一个servlet配置
                    System.out.println("发现 servlet");
                    //使用反射将该servlet实例放入到servletMapping
                    Element servletName = element.element("servlet-name");
                    Element servletClass = element.element("servlet-class");
                    servletMapping.put(servletName.getText(),
                            //trim()返回一个字符串，其值为此字符串，并删除任何前导空格和尾随空格。
                            (LilyHttpServlet) Class.forName(servletClass.getText().trim()).newInstance());
                } else if ("servlet-mapping".equalsIgnoreCase(element.getName())) {
                    //这是一个servlet-mapping
                    System.out.println("发现 servlet-mapping");
                    Element servletName = element.element("servlet-name");
                    Element servletUrl = element.element("url-pattern");
                    servletUrlMapping.put(servletUrl.getText(), servletName.getText());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //验证，这两个容器是否初始化成功
        System.out.println("servletMapping= " + servletMapping);
        System.out.println("servletUrlMapping= " + servletUrlMapping);
    }
}
