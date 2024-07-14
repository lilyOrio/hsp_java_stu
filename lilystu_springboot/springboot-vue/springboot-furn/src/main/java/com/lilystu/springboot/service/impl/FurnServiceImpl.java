package com.lilystu.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lilystu.springboot.bean.Furn;
import com.lilystu.springboot.mapper.FurnMapper;
import com.lilystu.springboot.service.FurnService;
import org.springframework.stereotype.Service;

@Service
public class FurnServiceImpl extends ServiceImpl<FurnMapper, Furn>
        implements FurnService {

}
