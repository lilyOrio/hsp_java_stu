package com.lilystu.furn.service;

import com.lilystu.furn.bean.Furn;
import com.lilystu.furn.bean.FurnExample;
import com.lilystu.furn.dao.FurnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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

    @Override
    public List<Furn> findAll() {
        //查看mapper.xml文件,此语句用于查询所有数据
        return furnMapper.selectByExample(null);
    }

    @Override
    public void update(Furn furn) {
        //因为传入的furn 的字段不一定是完整的，所以使用updateByPrimaryKeySelective
        furnMapper.updateByPrimaryKeySelective(furn);
    }

    @Override
    public void del(Integer id) {
        furnMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Furn findById(Integer id) {
        return furnMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Furn> findByCondition(String name) {
        FurnExample furnExample = new FurnExample();
        FurnExample.Criteria criteria = furnExample.createCriteria();
        if (StringUtils.hasText(name)){
            criteria.andNameLike("%" + name + "%");
        }
        return furnMapper.selectByExample(furnExample);
    }

}
