package org.shancm.serviceprovider.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.shancm.serviceprovider.entity.Product;
import org.shancm.serviceprovider.mapper.ProductMapper;
import org.shancm.serviceprovider.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * 商品表 服务层实现。
 *
 * @author shancm
 * @since 2026-02-21
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>  implements ProductService{

}
