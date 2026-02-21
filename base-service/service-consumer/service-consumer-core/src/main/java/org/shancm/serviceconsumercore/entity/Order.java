package org.shancm.serviceconsumercore.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 订单表 实体类。
 *
 * @author shancm
 * @since 2026-02-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "t_order", schema = "public")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 雪花ID主键
     */
    @Id
    private Long id;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 下单时的商品价格
     */
    private BigDecimal productPrice;

    /**
     * 订单总价
     */
    private BigDecimal totalAmount;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 订单状态：PENDING-待支付，PAID-已支付，CANCELLED-已取消
     */
    private String status;

    /**
     * 创建时间
     */
    private Timestamp createdAt;

    /**
     * 更新时间
     */
    private Timestamp updatedAt;

}
