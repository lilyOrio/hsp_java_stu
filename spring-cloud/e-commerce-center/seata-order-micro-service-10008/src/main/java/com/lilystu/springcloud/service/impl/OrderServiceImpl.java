package com.lilystu.springcloud.service.impl;

import com.lilystu.springcloud.dao.OrderDao;
import com.lilystu.springcloud.entity.Order;
import com.lilystu.springcloud.service.AccountService;
import com.lilystu.springcloud.service.OrderService;
import com.lilystu.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    @Override
    public void save(Order order) {
        log.info("=========开始新建订单 start ==========");
        //新建订单
        orderDao.save(order);
        System.out.println("order=" + order);

        log.info("=========减库存 start ==========");
        storageService.reduce(order.getProductId(), order.getNums());
        log.info("=========减库存 end ==========");

        log.info("=========减账户金额 start ==========");
        accountService.reduce(order.getUserId(), order.getMoney());
        log.info("=========减账户金额 end ==========");

        log.info("=========修改订单状态 start ==========");
        orderDao.update(order.getUserId(), 0);
        log.info("=========修改订单状态 end ==========");

        log.info("=========下订单 end==========");
    }
}
