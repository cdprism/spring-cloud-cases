package org.shancm.serviceprovidercore.mapper;

import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.shancm.serviceprovidercore.entity.Product;

/**
 * 商品表 映射层。
 *
 * @author shancm
 * @since 2026-02-21
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
