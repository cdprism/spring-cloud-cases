package org.shancm.serviceconsumercore.service.impl;

import org.apache.seata.spring.annotation.GlobalTransactional;
import org.shancm.serviceconsumercore.service.BusinessService;
import org.shancm.serviceconsumercore.service.OrderService;
import org.shancm.serviceproviderinterface.feign.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by shancm
 * @description BusinessServiceImpl
 * @since 2026-02-26 11:24
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    final
    OrderService orderService;
    final
    ProductFeignClient productFeignClient;

    public BusinessServiceImpl(OrderService orderService, ProductFeignClient productFeignClient) {
        this.orderService = orderService;
        this.productFeignClient = productFeignClient;
    }

    @GlobalTransactional(name = "order", rollbackFor = Exception.class)
    @Override
    public void order(int quantity) {

        orderService.create(quantity);

        productFeignClient.reduceProductByCreateOrder(1700000000000000001L, quantity);


    }
}
