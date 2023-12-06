package com.lily_stu.spring.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class MultiplyTxService {
    @Autowired
    private GoodsService goodService;

    @Transactional
    public void multiTxTest() {
        //2 号用户购买2 号商品，购买数量是1 个.
        goodService.buyGoodsByTx(2, 2, 1);
        //1 号用用户1 号商品，购买数量是1 个.
        goodService.buyGoodsByTx02(1, 1, 1);
    }
}
