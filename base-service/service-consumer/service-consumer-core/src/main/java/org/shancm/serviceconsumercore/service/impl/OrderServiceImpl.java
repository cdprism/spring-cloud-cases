package org.shancm.serviceconsumercore.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.shancm.serviceconsumercore.entity.Order;
import org.shancm.serviceconsumercore.mapper.OrderMapper;
import org.shancm.serviceconsumercore.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 订单表 服务层实现。
 *
 * @author shancm
 * @since 2026-02-21
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>  implements OrderService{

    final
    OrderMapper orderMapper;

    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Transactional
    @Override
    public void create(int quantity) {
        BigDecimal price = new BigDecimal("8.50");

        Order order = Order.builder().id(System.currentTimeMillis()).productId(1700000000000000001L)
                .quantity(quantity).productPrice(price).totalAmount(price.multiply(new BigDecimal(quantity))).build();

        orderMapper.insert(order);
    }

}
