package com.lilystu.springboot.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lilystu.springboot.mybatisplus.bean.Monster;
import com.lilystu.springboot.mybatisplus.dao.MonsterMapper;
import com.lilystu.springboot.mybatisplus.service.MonsterService;
import org.springframework.stereotype.Service;

@Service
public class MonsterServiceImpl extends ServiceImpl<MonsterMapper, Monster> implements MonsterService {
}
