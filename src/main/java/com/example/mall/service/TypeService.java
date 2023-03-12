package com.example.mall.service;

import com.example.mall.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mall.pojo.vo.TypeVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linyes
 * @since 2023-02-26
 */
public interface TypeService extends IService<Type> {

    List<TypeVO> getTypeInfo();
}
