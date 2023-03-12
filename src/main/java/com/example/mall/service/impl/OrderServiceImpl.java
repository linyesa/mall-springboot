package com.example.mall.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.example.mall.pojo.Address;
import com.example.mall.pojo.Goods;
import com.example.mall.pojo.Order;
import com.example.mall.mapper.OrderMapper;
import com.example.mall.pojo.vo.OrderVO;
import com.example.mall.service.AddressService;
import com.example.mall.service.GoodsService;
import com.example.mall.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mall.utils.R;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private AddressService addressService;
    @Autowired
    private GoodsService goodsService;
    @Override

    public R createOrder(OrderVO orderVO) {
        Address address = addressService.query().eq("address_id", orderVO.getAddressId()).one();
        if(ObjectUtil.isEmpty(address)){
            return R.error("地址不能为空");
        }
        Goods goods = goodsService.query().eq("goods_id", orderVO.getAddressId()).one();
        if(ObjectUtil.isEmpty(goods)){
            return R.error("商品不存在");
        }
        Order order=new Order();
        order.setOrderNo(String.valueOf(IdUtil.getSnowflake(1, 20).nextId()));
        order.setUserId(orderVO.getUserId());
        order.setTotalPrice(goods.getSellingPrice());
        order.setPayStatus(1L);
        order.setPayTime(new Date());
        order.setUserName(address.getName());
        order.setUserAddress(address.getAddress());
        order.setUserPhone(address.getPhone());
        order.setGoodsId(goods.getGoodsId());
        order.setOrderStatus(1L);
        order.setExtraInfo("body");
        save(order);
        return R.ok("下单购买成功");
    }
}
