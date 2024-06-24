package com.lilystu.springboot.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class ParameterController {

    @GetMapping("/monster/{id}/{name}")
    //获取路径参数
    public String pathVariable(@PathVariable("id") Integer idx,
                               @PathVariable("name") String name,
                               @PathVariable Map<String, String> map) {
        return idx + "--" + name + "\n map = " + map;
        //100--king map = {id=100, name=king}
    }

    @GetMapping("/requestHeader")
    //@RequestHeader("Host") 获取http请求头的host信息
    //@RequestHeader Map<String,String>获取请求头所有信息
    public String requestHeader(@RequestHeader("Host") String host,
                                @RequestHeader Map<String, String> head) {
        return "host - " + host + "    head - \n" + head;
        //host - localhost:10001
        // head - {host=localhost:10001, connection=keep-alive, sec-ch-ua="Not/A)Brand";v="8",
        // "Chromium";v="126", "Microsoft Edge";v="126", sec-ch-ua-mobile=?0,
        // sec-ch-ua-platform="Windows", upgrade-insecure-requests=1,
        // user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0,
        // accept=text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7,
        // sec-fetch-site=same-origin, sec-fetch-mode=navigate, sec-fetch-user=?1, sec-fetch-dest=document,
        // referer=http://localhost:10001/lily/index.html, accept-encoding=gzip, deflate, br, zstd, accept-language=zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6}
    }

    @GetMapping("/requestParam")
    //获取表单提交参数
    // /requestParam?name=lily&fruit=apple&fruit=banana
    public String requestParam(@RequestParam(value = "name") String username,
                               @RequestParam(value = "fruit") List<String> fruits,
                               @RequestParam Map<String, String> map) {
        return "username = " + username + ", fruits = " + fruits + ", map = " + map;
        //username = lily, fruits = [apple, banana], map = {name=lily, fruit=apple}
    }

    @GetMapping("/cookieValue")
    //获取cookie值
    //@CookieValue(value = "cookie_key",required = false) String cookieKey 以String方式直接获取cookie值
    //@CookieValue("username") Cookie cookie 以cookie 对象方式返回
    public String cookieValue(@CookieValue(value = "cookie_key",required = false) String cookieKey,
                              @CookieValue("username") Cookie cookie,
                              HttpServletRequest request) {
        return "cookieKey = " + cookieKey + ", username = "
                + cookie.getName() + "-" + cookie.getValue() + "request = " + Arrays.toString(request.getCookies());
// cookieKey = ok~, username = username-lily
// request = [javax.servlet.http.Cookie@2496ba36, javax.servlet.http.Cookie@31ec6c13]
// 需要在浏览器设置cookie
    }

    @PostMapping("/requestBody")
    //获取post请求体
    public String requestBody(@RequestBody String context) {
        return "context = " + context;
        //context = name=lily&age=hello
    }

}
