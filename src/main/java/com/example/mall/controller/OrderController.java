package com.example.mall.controller;


import com.example.mall.pojo.vo.OrderVO;
import com.example.mall.service.OrderService;
import com.example.mall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author linyes
 * @since 2023-02-26
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("createOrder")
    public R createOrder(@RequestBody OrderVO orderVO){
        return orderService.createOrder(orderVO);
    }
}

