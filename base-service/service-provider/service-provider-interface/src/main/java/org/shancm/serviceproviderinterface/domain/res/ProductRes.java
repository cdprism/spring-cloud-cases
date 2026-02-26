package org.shancm.serviceproviderinterface.domain.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author by shancm
 * @description TODO
 * @since 2026-02-21 22:25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRes implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 雪花ID主键
     */
    private Long id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品库存
     */
    private Integer stock;

    /**
     * 创建时间
     */
    private Timestamp createdAt;

    /**
     * 更新时间
     */
    private Timestamp updatedAt;
}
