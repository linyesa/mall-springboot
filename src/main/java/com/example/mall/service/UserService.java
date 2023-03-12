package com.example.mall.service;

import com.example.mall.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mall.pojo.vo.RegisterVo;
import com.example.mall.pojo.vo.UserInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linyes
 * @since 2023-02-26
 */
public interface UserService extends IService<User> {

   UserInfo login(User user);

    void register(RegisterVo registerVo);

    Long getUserIdByMobile(String mobile);
}
