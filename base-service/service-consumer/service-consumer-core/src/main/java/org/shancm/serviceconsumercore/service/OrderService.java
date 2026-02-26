package org.shancm.serviceconsumercore.service;

import com.mybatisflex.core.service.IService;
import org.shancm.serviceconsumercore.entity.Order;

/**
 * 订单表 服务层。
 *
 * @author shancm
 * @since 2026-02-21
 */
public interface OrderService extends IService<Order> {

    void create(int quantity);

}
