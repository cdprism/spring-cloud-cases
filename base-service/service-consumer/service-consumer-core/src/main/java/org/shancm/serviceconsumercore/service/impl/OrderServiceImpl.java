package org.shancm.serviceconsumercore.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.shancm.serviceconsumercore.entity.Order;
import org.shancm.serviceconsumercore.mapper.OrderMapper;
import org.shancm.serviceconsumercore.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * 订单表 服务层实现。
 *
 * @author shancm
 * @since 2026-02-21
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>  implements OrderService{

}
