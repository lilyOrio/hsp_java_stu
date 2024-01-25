package com.lily_stu.web.debug;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HelloHandler {
    @RequestMapping(value = "/debug/springmvc")
    public ModelAndView hello(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ok");
        modelAndView.addObject("name", "lily");
        return modelAndView;
    }
}
