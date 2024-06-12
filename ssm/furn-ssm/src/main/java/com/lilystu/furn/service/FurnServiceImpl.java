package com.lilystu.furn.service;

import com.lilystu.furn.bean.Furn;
import com.lilystu.furn.dao.FurnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FurnServiceImpl implements FurnService{

    //这里也可以使用@Resource
    @Autowired
    FurnMapper furnMapper;

    @Override
    public void save(Furn furn) {
//因为id 是自增长的，所以是部分字段，选择insertSelective
        furnMapper.insertSelective(furn);
    }
}