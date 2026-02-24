package org.shancm.serviceprovidercore.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mybatisflex.core.paginate.Page;
import org.shancm.common.domain.Result;
import org.shancm.common.domain.enums.ResultCode;
import org.shancm.common.exception.BusinessException;
import org.shancm.common.util.JsonUtils;
import org.shancm.serviceprovidercore.config.property.ProviderProperty;
import org.shancm.serviceprovidercore.entity.Product;
import org.shancm.serviceprovidercore.service.ProductService;
import org.shancm.serviceproviderinterface.domain.req.ProductReq;
import org.shancm.serviceproviderinterface.domain.res.ProductRes;
import org.shancm.serviceproviderinterface.feign.ServiceProductFeignClient;
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
public class ProductController implements ServiceProductFeignClient {

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

    /*@GetMapping("/e2")
    public void e2() throws Exception {
        List<Integer> list = null;
        list.getFirst();
    }*/

    /**
     * 查询所有商品表。
     *
     * @return 所有数据
     */
    @Override
    @GetMapping("list")
    public Result<List<ProductRes>> list() {
        return Result.success(JsonUtils.parseArray(productService.list().toString(), ProductRes.class));
    }

    /**
     * 根据主键获取商品表。
     *
     * @param id 商品表主键
     * @return 商品表详情
     */
    @Override
    @GetMapping("getInfo/{id}")
    public Result<ProductRes> getInfo(@PathVariable Long id) {

        return Result.success(JsonUtils.parseObject(Objects.requireNonNull(productService.getById(id)).toString(), ProductRes.class));
    }


}
