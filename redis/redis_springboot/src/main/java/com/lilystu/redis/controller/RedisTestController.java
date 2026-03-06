package com.lilystu.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: lily
 * @Version: 1.0
 */
@RestController
@RequestMapping("/redisTest")
public class RedisTestController {

    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("/t1")
    public String t1(){
        redisTemplate.opsForValue().set("book","天龙八部");
//        redisTemplate.opsForHash()
//        redisTemplate.opsForSet()
//        redisTemplate.opsForZSet()
//        redisTemplate.opsForList()
        String book = (String) redisTemplate.opsForValue().get("book");
        return book;
    }
}
