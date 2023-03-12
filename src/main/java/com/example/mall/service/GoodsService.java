package com.example.mall.service;

import com.example.mall.pojo.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mall.pojo.vo.SearchVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linyes
 * @since 2023-02-26
 */
public interface GoodsService extends IService<Goods> {

    List<Goods> searchGoods(SearchVO searchVO);

    List<Goods> getGoodsInfoByUserId(Long userId,Integer status);
}
