package com.lily_stu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cur")
public class ComputerHandler {
    @PostMapping("/save")
    public String save(String brand, String price, String nums) {
        System.out.println("提交信息：brand=" + brand + "  price=" + price + "  nums=" + nums);
        return "success";
    }
}
