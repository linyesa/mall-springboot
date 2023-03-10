package com.example.mall.service;

import com.example.mall.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mall.pojo.vo.OrderVO;
import com.example.mall.utils.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linyes
 * @since 2023-02-26
 */
public interface OrderService extends IService<Order> {

    R createOrder(OrderVO orderVO);
}
