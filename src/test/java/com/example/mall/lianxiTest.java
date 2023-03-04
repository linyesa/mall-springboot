package com.example.mall;

import com.example.mall.mapper.UserMapper;
import com.example.mall.pojo.User;
import com.example.mall.utils.MD5;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = MallApplication.class)
@RunWith(SpringRunner.class)
@MapperScan("com.example.mall.mapper")
@ComponentScan("com.example.mall")
public class lianxiTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Test
    public void TestInsertUser(){
        User user=new User();
        user.setUsername("lys");
        user.setMobile("17783052743");
        user.setPassword("123456");
        userMapper.insert(user);
    }
    @Test
    public void TestMd5(){
        //e10adc3949ba59abbe56e057f20f883e
        System.out.println(MD5.encrypt("123456"));
        //63a9f0ea7bb98050796b649e85481845
        System.out.println(MD5.encrypt("root"));
    }
    @Test
    public void TestStringHasLength(){
        System.out.println(StringUtils.hasLength(""));
    }
    @Test
    public void TestRedis(){
        //(phone,code,5, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set("test","1235435",5,TimeUnit.MINUTES);
        String redisCode = redisTemplate.opsForValue().get("test");
        System.out.println(redisCode);
    }
}
