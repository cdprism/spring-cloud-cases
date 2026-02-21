package org.shancm.serviceconsumer.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.shancm.serviceconsumer.entity.Order;
import org.shancm.serviceconsumer.mapper.OrderMapper;
import org.shancm.serviceconsumer.service.OrderService;
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
