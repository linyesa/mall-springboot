package com.example.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mall.pojo.Goods;
import com.example.mall.mapper.GoodsMapper;
import com.example.mall.pojo.vo.SearchVO;
import com.example.mall.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Override
    public List<Goods> searchGoods(SearchVO searchVO) {
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();
        if(StringUtils.hasLength(searchVO.getKeyWord())){
            queryWrapper.like("goods_name",searchVO.getKeyWord());
        }
        if(!(null==searchVO.getTypeId())){
            queryWrapper.eq("goods_category_id",searchVO.getTypeId());
        }
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Goods> getGoodsInfoByUserId(Long userId,Integer status) {
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("create_user",userId);
        queryWrapper.eq("goods_sell_status",status);
        return baseMapper.selectList(queryWrapper);
    }
}
