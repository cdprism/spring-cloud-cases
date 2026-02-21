package org.shancm.serviceprovider.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import org.shancm.serviceprovider.entity.Product;

/**
 * 商品表 映射层。
 *
 * @author shancm
 * @since 2026-02-21
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
