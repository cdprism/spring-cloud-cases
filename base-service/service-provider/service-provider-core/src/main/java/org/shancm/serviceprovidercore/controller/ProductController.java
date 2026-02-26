package org.shancm.serviceprovidercore.controller;

import org.apache.seata.core.context.RootContext;
import org.shancm.common.domain.Result;
import org.shancm.common.domain.enums.ResultCode;
import org.shancm.common.exception.BusinessException;
import org.shancm.common.exception.DataException;
import org.shancm.common.util.JsonUtils;
import org.shancm.serviceprovidercore.config.property.ProviderProperty;
import org.shancm.serviceprovidercore.entity.Product;
import org.shancm.serviceprovidercore.service.ProductService;
import org.shancm.serviceproviderinterface.domain.req.ProductReq;
import org.shancm.serviceproviderinterface.domain.res.ProductRes;
import org.shancm.serviceproviderinterface.feign.ProductFeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 商品表 控制层。
 *
 * @author shancm
 * @since 2026-02-21
 */
@RestController
@RequestMapping("/product")
public class ProductController implements ProductFeignClient {

    private final ProductService productService;
    private final ProviderProperty providerProperty;

    public ProductController(ProductService productService,
                             ProviderProperty providerProperty) {
        this.productService = productService;
        this.providerProperty = providerProperty;
    }

    @Override
    @PostMapping("/post-test")
    public String postTest(@RequestBody ProductReq productReq) {
        return productReq.toString();
    }

    @Override
    @GetMapping("/config")
    public String checkConfig() {
        System.out.println(providerProperty.getDateTime());
        return "DateTime is " + providerProperty.getDateTime();
    }

    @GetMapping("/e1")
    public void e1() throws BusinessException {
        throw new BusinessException(ResultCode.SYSTEM_ERROR);
    }

    /**
     * 查询所有商品表。
     *
     * @return 所有数据
     */
    @Override
    @GetMapping("/list")
    public Result<List<ProductRes>> list() {
        return Result.success(JsonUtils.parseArray(productService.list().toString(), ProductRes.class));
    }

    @Override
    @PostMapping("/reduceProductByCreateOrder")
    public Result<ProductRes> reduceProductByCreateOrder(@RequestParam("id") long id,
                                                         @RequestParam("quantity") int quantity) throws DataException {

        System.out.println("ProductController RootContext.getXID() : "+RootContext.getXID());

        Product product = productService.getById(id);
        assert product != null;

        product.setStock(product.getStock() - quantity);

        productService.reduct(product);

        if(quantity>5){
            throw new DataException("0000");
        }

        ProductRes productRes = JsonUtils.convertValue(product, ProductRes.class);
        System.out.println(productRes);

        return Result.success(productRes);

    }

    /**
     * 根据主键获取商品表。
     *
     * @param id 商品表主键
     * @return 商品表详情
     */
    @Override
    @GetMapping("/getInfo/{id}")
    public Result<ProductRes> getInfo(@PathVariable Long id) {

        return Result.success(JsonUtils.parseObject(Objects.requireNonNull(productService.getById(id)).toString(), ProductRes.class));
    }


}
