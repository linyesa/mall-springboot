package com.example.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mall.exceptionhandler.LysException;
import com.example.mall.pojo.User;
import com.example.mall.mapper.UserMapper;
import com.example.mall.pojo.vo.RegisterVo;
import com.example.mall.pojo.vo.UserInfo;
import com.example.mall.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mall.utils.MD5;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linyes
 * @since 2023-02-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public UserInfo login(User user) {
        //获取登录手机号和密码
        String mobile = user.getMobile();
        String password = user.getPassword();
//手机号和密码非空判断

        if(!StringUtils.hasLength(mobile) || !StringUtils.hasLength(password)) {
            throw new LysException(20001,"手机号或密码为空，登录失败");
        }
        //判断手机号是否正确
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        User mobileMember = baseMapper.selectOne(wrapper);
        //判断查询对象是否为空
        if(mobileMember == null) {//没有这个手机号
            throw new LysException(20001,"手机号绑定用户不存在，登录失败");
        }

        //判断密码
        //因为存储到数据库密码肯定加密的
        //把输入的密码进行加密，再和数据库密码进行比较
        //加密方式 MD5
        if(!MD5.encrypt(password).equals(mobileMember.getPassword())) {

            throw new LysException(20001,"密码错误，登录失败");
        }

        //判断用户是否禁用
        if(mobileMember.getStatus()==0) {
            throw new LysException(20001,"用户被禁用，登录失败");
        }
        UserInfo userInfo=new UserInfo();
        BeanUtils.copyProperties(mobileMember,userInfo);
        return userInfo;
    }

    @Override
    public void register(RegisterVo registerVo) {
//获取注册的数据
        String code = registerVo.getCode(); //验证码
        String mobile = registerVo.getMobile(); //手机号
        String username = registerVo.getUsername(); //昵称
        String password = registerVo.getPassword(); //密码

        //非空判断
        if(!StringUtils.hasLength(mobile) || !StringUtils.hasLength(password)
                || !StringUtils.hasLength(code) || !StringUtils.hasLength(username)) {
            throw new LysException(20001,"注册失败");
        }
//        判断验证码
//        获取redis验证码
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if(!code.equals(redisCode)) {
            throw new LysException(20001,"注册失败");
        }
        //判断手机号是否重复，表里面存在相同手机号不进行添加
        QueryWrapper<User> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(queryWrapper);
        if(count > 0) {
            throw new LysException(20001,"手机号已经注册，注册失败");
        }

        //数据添加数据库中
        User user=new User();
        password=MD5.encrypt(password);
        user.setPassword(password);
        user.setUsername(username);
        user.setMobile(mobile);
          baseMapper.insert(user);
    }

    @Override
    public Long getUserIdByMobile(String mobile) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        User user=baseMapper.selectOne(queryWrapper);
        return user.getUserId();
    }
}
