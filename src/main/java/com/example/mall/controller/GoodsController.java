package com.example.mall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mall.mapper.GoodsMapper;
import com.example.mall.pojo.Goods;
import com.example.mall.pojo.vo.SearchVO;
import com.example.mall.service.GoodsService;
import com.example.mall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author linyes
 * @since 2023-02-26
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsMapper goodsMapper;
    @GetMapping("getGoodsInfo")
    public R getGoodsInfo(){
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("goods_sell_status",1);
        List<Goods> goodsList=goodsMapper.selectList(queryWrapper);
        return R.ok().put("goodsInfo",goodsList);
    }
    @GetMapping("getDetailById/{id}")
    public R getDetailById(@PathVariable("id")Long id){
        Goods goods=goodsService.getById(id);
        return R.ok().put("goodsDetail",goods);
    }
    @PostMapping("searchGoods")
    public R searchGoods(@RequestBody SearchVO searchVO){
        List<Goods> goodsList=goodsService.searchGoods(searchVO);
        return R.ok().put("goodsInfo",goodsList);
    }
    @PostMapping("saveGoods")
    public R saveGoods(@RequestBody Goods goods){
        goods.setGoodsSellStatus(1);
        goodsMapper.insert(goods);
        return R.ok();
    }
    @GetMapping("getOnGoodsInfoByUserId/{userId}/{status}")
    public R getOnGoodsInfoByUserId(@PathVariable("userId") Long userId,@PathVariable("status") Integer status){
        List<Goods> goodsList=goodsService.getGoodsInfoByUserId(userId,status);
        return R.ok().put("goodsList",goodsList);
    }

}

