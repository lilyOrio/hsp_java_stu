package com.lilystu.tomcat.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * 封装http请求的数据
 * 请求方法、uri、参数列表
 * 先考虑GET请求
 */
public class LilyRequest {
    private String method;
    private String uri;
    private HashMap<String, String> parametersMapping = new HashMap<>();
    private InputStream inputStream;

    public LilyRequest(InputStream inputStream) {//这里的InputStream是和http请求的Socket关联的Stream
        this.inputStream = inputStream;
        init();
    }

    private void init() {
        try {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            /**
             * GET /hspCalServlet?num1=10&num2=30 HTTP/1.1
             * Host: localhost:8080
             * User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:97.0) Gecko/20100101 Fi
             */
//            获取method
            String requestLine = reader.readLine();
            String[] requestLineArr = requestLine.split(" ");
            method = requestLineArr[0];
//            获取uri
            int index = requestLineArr[1].indexOf("?");
            if (index != -1) {//如果拥有参数列表
                uri = requestLineArr[1].substring(0, index);
                //获取参数列表->parametersMapping
                //parameters => num1=10&num2=30
                String parameters = requestLineArr[1].substring(index + 1);
                String[] parametersPair = parameters.split("&");
                //防止用户提交时 /LilyCalServlet?
                if (null != parametersPair || !"".equals(parametersPair)) {
                    //再次分割 parameterPair ==> num1=10
                    for (String parameterPair : parametersPair) {
                        //parameterVal ["num1", "10"]
                        String[] parameterVal = parameterPair.split("=");
                        if (parameterVal.length == 2) {
                            //放入到 parametersMapping
                            parametersMapping.put(parameterVal[0], parameterVal[1]);
                        }
                    }
                }
            } else {
                uri = requestLineArr[1];
            }
            //这里不能关闭流 inputStream 和 socket关联,关闭该流，会导致与其关联的Socket关闭
            //inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //request对象有一个特别重要方法
    public String getParameter(String name) {
        if (parametersMapping.containsKey(name)) {
            return parametersMapping.get(name);
        } else {
            return "";
        }
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "HspRequest{" +
                "method='" + method + '\'' +
                ", uri='" + uri + '\'' +
                ", parametersMapping=" + parametersMapping +
                '}';
    }
}
