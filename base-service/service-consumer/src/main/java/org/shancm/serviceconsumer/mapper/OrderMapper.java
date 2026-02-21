package org.shancm.serviceconsumer.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import org.shancm.serviceconsumer.entity.Order;

/**
 * 订单表 映射层。
 *
 * @author shancm
 * @since 2026-02-21
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
