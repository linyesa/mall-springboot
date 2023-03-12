package com.example.mall.controller;


import com.example.mall.pojo.vo.TypeVO;
import com.example.mall.service.TypeService;
import com.example.mall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @GetMapping("typeInfo")
    public R getTypeInfo(){
        List<TypeVO> typeVOList=typeService.getTypeInfo();
        return R.ok().put("typeVOList",typeVOList);
    }
}

