package org.shancm.serviceprovidercore.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.shancm.serviceprovidercore.entity.Product;
import org.shancm.serviceprovidercore.mapper.ProductMapper;
import org.shancm.serviceprovidercore.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品表 服务层实现。
 *
 * @author shancm
 * @since 2026-02-21
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>  implements ProductService{

    final
    ProductMapper productMapper;

    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Transactional
    @Override
    public void reduct(Product product) {
        productMapper.update(product);
    }
}
