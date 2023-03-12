package com.example.mall.controller;


import com.example.mall.pojo.User;
import com.example.mall.pojo.vo.RegisterVo;
import com.example.mall.pojo.vo.UserInfo;
import com.example.mall.service.UserService;
import com.example.mall.utils.R;
import com.example.mall.utils.RandomUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author linyes
 * @since 2023-02-26
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private UserService userService;
    @PostMapping("login")
    public R login(@RequestBody User user){
        UserInfo userInfo=userService.login(user);
        if(userInfo!=null){
            return R.ok().put("userinfo",userInfo);
        }else {
            return R.error(20001,"登录失败");
        }
    }
    //注册
    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo registerVo) {
        userService.register(registerVo);
        return R.ok();
    }
    @GetMapping("sendcode/{mobile}")
    public R sendCode(@PathVariable String mobile){
        String code = redisTemplate.opsForValue().get(mobile);
        if(StringUtils.hasLength(code)) {
            return R.ok();
        }
        //2 如果redis获取 不到，生成code
        //生成随机值，传递阿里云进行发送
        code = RandomUtil.getFourBitRandom();
        redisTemplate.opsForValue().set(mobile,code,5, TimeUnit.MINUTES);
        return R.ok();
    }
    @GetMapping("testaxios")
    public R testAxios(){
        return R.ok("cheskdfhlakd");
    }
    @GetMapping("getUserIdByMobile")
    public R getUserIdByMobile(@RequestParam String mobile){
        Long userId=userService.getUserIdByMobile(mobile);
        return R.ok().put("userId",userId);
    }
}

