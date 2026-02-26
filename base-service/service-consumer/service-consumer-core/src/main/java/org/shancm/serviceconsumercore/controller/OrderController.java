package org.shancm.serviceconsumercore.controller;

import com.mybatisflex.core.paginate.Page;
import org.shancm.common.domain.Result;
import org.shancm.serviceconsumercore.entity.Order;
import org.shancm.serviceconsumercore.service.BusinessService;
import org.shancm.serviceconsumercore.service.OrderService;
import org.shancm.serviceproviderinterface.domain.req.ProductReq;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单表 控制层。
 *
 * @author shancm
 * @since 2026-02-21
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final BusinessService businessService;


    public OrderController(OrderService orderService,BusinessService businessService) {
        this.orderService = orderService;
        this.businessService = businessService;
    }

    @GetMapping("/show")
    public String show(){

//        return productFeignClient.list().getData().toString();

        ProductReq req = new ProductReq();
        req.setProductName("测试");
        req.setStock(999);
//        return productFeignClient.postTest(req);
        return "";

    }

    @PostMapping("/takeOrder")
    public Result<?> takeOrder(@RequestBody int quantity)  {

        businessService.order(quantity);

        return Result.success();
    }

    /**
     * 查询所有订单表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<Order> list() {
        return orderService.list();
    }

    /**
     * 根据主键获取订单表。
     *
     * @param id 订单表主键
     * @return 订单表详情
     */
    @GetMapping("getInfo/{id}")
    public Order getInfo(@PathVariable Long id) {
        return orderService.getById(id);
    }

    /**
     * 分页查询订单表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<Order> page(Page<Order> page) {
        return orderService.page(page);
    }

}
