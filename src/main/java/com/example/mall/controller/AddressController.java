package com.example.mall.controller;


import com.example.mall.service.AddressService;
import com.example.mall.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @GetMapping("getAddressInfoByUserId/{userId}")
    public R getAddressInfoByUserId(@PathVariable("userId") String userId){
        return R.ok().put("addressInfo",addressService.query().eq("user_id",userId).list());
    }
}

