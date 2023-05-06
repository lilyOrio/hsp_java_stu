package com.lilystu.tomcat.servlet;

import com.lilystu.tomcat.http.LilyRequest;
import com.lilystu.tomcat.http.LilyResponse;

import java.io.IOException;

public abstract class LilyHttpServlet implements LilyServlet {
    @Override
    public void init() throws Exception {

    }

    @Override
    public void service(LilyRequest request, LilyResponse response) throws IOException {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            this.doGet(request, response);
        } else if ("POST".equalsIgnoreCase(request.getMethod())) {
            this.doPost(request, response);
        }
    }

    @Override
    public void destroy() {

    }

    public void doGet(LilyRequest request, LilyResponse response) throws IOException {

    }

    public void doPost(LilyRequest request, LilyResponse response) throws IOException {

    }
}
