package com.example.mall.service.impl;

import com.example.mall.pojo.Order;
import com.example.mall.mapper.OrderMapper;
import com.example.mall.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linyes
 * @since 2023-02-26
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
