package org.shancm.serviceproviderinterface.feign;

import com.mybatisflex.core.paginate.Page;
import org.shancm.common.domain.Result;
import org.shancm.serviceproviderinterface.domain.req.ProductReq;
import org.shancm.serviceproviderinterface.domain.res.ProductRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author shancm
 */
@FeignClient("service-provider")
@RequestMapping("/product")
public interface ServiceProductFeignClient {

    @GetMapping("/config")
    String checkConfig();

    /**
     * 保存商品表。
     *
     * @param product 商品表
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    /*@PostMapping("save")
    boolean save(@RequestBody ProductReq product);*/

    /**
     * 根据主键删除商品表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    /*@DeleteMapping("remove/{id}")
    boolean remove(@PathVariable Long id);*/

    /**
     * 根据主键更新商品表。
     *
     * @param product 商品表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    /*@PutMapping("update")
    boolean update(@RequestBody ProductReq product);*/

    /**
     * 查询所有商品表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    Result<List<ProductRes>> list();

    /**
     * 根据主键获取商品表。
     *
     * @param id 商品表主键
     * @return 商品表详情
     */
    @GetMapping("getInfo/{id}")
    Result<ProductRes> getInfo(@PathVariable Long id);

    /**
     * 分页查询商品表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    Result<Page<ProductRes>> page(Page<ProductReq> page);


}
