package com.lilystu.furns.test;

import com.lilystu.furns.entity.Furn;
import com.lilystu.furns.entity.Page;
import com.lilystu.furns.service.FurnsService;
import com.lilystu.furns.service.impl.FurnsServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class FurnsServiceTest {
    private FurnsService furnsService = new FurnsServiceImpl();

    @Test
    public void queryFurns() {
        List<Furn> furnList = furnsService.queryFurns();
        for (Furn f : furnList) {
            System.out.println(f);
        }
    }

    @Test
    public void addFurn(){
        Furn furn = new Furn(null, "可爱沙发~", "L家具",
                new BigDecimal(999.99), 100, 10,
                "assets/images/product-image/16.jpg");
        System.out.println("添加家具：" + furnsService.addFurn(furn));
    }

    @Test
    public void deleteFurnById(){
        System.out.println("输出家具：" + furnsService.deleteFurnById(5));
    }

    @Test
    public void queryFurnById(){
        System.out.println("找到家具：" + furnsService.queryFurnById(2));
    }

    @Test
    public void updateFurn(){
        Furn furn = new Furn(3, "可爱沙发~~", "L家具",
                new BigDecimal(999.99), 100, 10,
                "assets/images/product-image/16.jpg");
        System.out.println("更新家具信息：" + furnsService.updateFurn(furn));
    }

    @Test
    public void page(){
        Page page = furnsService.page(2, 3);
        System.out.println("分页信息：" +page);
    }

    @Test
    public void pageByName(){
        Page<Furn> page = furnsService.pageByName(2, 2,"沙发");
        System.out.println("分页信息：" +page);
    }
}
