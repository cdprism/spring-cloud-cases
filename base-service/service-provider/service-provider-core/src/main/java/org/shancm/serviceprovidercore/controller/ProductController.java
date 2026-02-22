package org.shancm.serviceprovidercore.controller;

import com.mybatisflex.core.paginate.Page;
import org.shancm.serviceprovidercore.config.property.ProviderProperty;
import org.shancm.serviceprovidercore.entity.Product;
import org.shancm.serviceprovidercore.service.ProductService;
import org.shancm.serviceproviderinterface.domain.req.ProductReq;
import org.shancm.serviceproviderinterface.domain.res.ProductRes;
import org.shancm.serviceproviderinterface.feign.ServiceProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/config")
    public String checkConfig() {
        System.out.println(providerProperty.getDateTime());
        return "DateTime is " + providerProperty.getDateTime();
    }


    /**
     * 保存商品表。
     *
     * @param product 商品表
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @Override
    @PostMapping("save")
    public boolean save(@RequestBody ProductReq product) {
        return productService.save(product);
    }

    /**
     * 根据主键删除商品表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @Override
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return productService.removeById(id);
    }


    /**
     * 根据主键更新商品表。
     *
     * @param product 商品表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @Override
    @PutMapping("update")
    public boolean update(@RequestBody ProductReq product) {
        return productService.updateById(product);
    }

    /**
     * 查询所有商品表。
     *
     * @return 所有数据
     */
    @Override
    @GetMapping("list")
    public List<ProductRes> list() {
        return productService.list();
    }

    /**
     * 根据主键获取商品表。
     *
     * @param id 商品表主键
     * @return 商品表详情
     */
    @Override
    @GetMapping("getInfo/{id}")
    public ProductRes getInfo(@PathVariable Long id) {
        return productService.getById(id);
    }

    /**
     * 分页查询商品表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @Override
    @GetMapping("page")
    public Page<ProductRes> page(Page<ProductReq> page) {
        return productService.page(page);
    }

}
