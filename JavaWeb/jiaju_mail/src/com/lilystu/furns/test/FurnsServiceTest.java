package com.lilystu.furns.test;

import com.lilystu.furns.entity.Furn;
import com.lilystu.furns.service.FurnsService;
import com.lilystu.furns.service.impl.FurnsServiceImpl;
import org.junit.jupiter.api.Test;

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
}
