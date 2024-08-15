package com.lilystu.springcloud.service.impl;

import com.lilystu.springcloud.dao.StorageDao;
import com.lilystu.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {


    @Resource
    private StorageDao storageDao;

    @Override
    public void reduce(Long productId, Integer nums) {
        log.info("==========seata_storage_micro_service-10010 扣 减 库 存 start==========");
        storageDao.reduce(productId, nums);
        log.info("==========seata_storage_micro_service-10010 扣 减 库 存 end==========");
    }
}
