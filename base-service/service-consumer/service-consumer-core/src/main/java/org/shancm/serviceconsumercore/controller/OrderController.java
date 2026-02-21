package org.shancm.serviceconsumercore.controller;

import com.mybatisflex.core.paginate.Page;
import org.shancm.serviceconsumercore.entity.Order;
import org.shancm.serviceconsumercore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private OrderService orderService;

    /**
     * 保存订单表。
     *
     * @param order 订单表
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody Order order) {
        return orderService.save(order);
    }

    /**
     * 根据主键删除订单表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return orderService.removeById(id);
    }

    /**
     * 根据主键更新订单表。
     *
     * @param order 订单表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody Order order) {
        return orderService.updateById(order);
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
