package com.lilystu.springboot;

import com.lilystu.springboot.bean.Furn;
import com.lilystu.springboot.mapper.FurnMapper;
import com.lilystu.springboot.service.FurnService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class FurnTest {

    @Resource
    FurnMapper furnMapper;

    @Resource
    FurnService furnService;

    @Test
    public void testMapper() {
        System.out.println("furnMapper--" + furnMapper.getClass());
        Furn furn = furnMapper.selectById(1);
        System.out.println("furn=" + furn);
    }

    @Test
    public void testService() {
        List<Furn> list = furnService.list();
        for (Furn furn : list) {
            System.out.println("furn=" + furn);
        }
    }
}
