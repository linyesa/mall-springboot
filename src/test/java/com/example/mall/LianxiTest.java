package com.example.mall;

import com.example.mall.mapper.GoodsMapper;
import com.example.mall.mapper.UserMapper;
import com.example.mall.pojo.Goods;
import com.example.mall.pojo.User;
import com.example.mall.service.GoodsService;
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
public class LianxiTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private GoodsMapper goodsMapper;
    @Test
    public void testInsertUser(){
        User user=new User();
        user.setUsername("lys");
        user.setMobile("17783052743");
        user.setPassword("123456");
        userMapper.insert(user);
    }
    @Test
    public void testMd5(){
        //e10adc3949ba59abbe56e057f20f883e
        System.out.println(MD5.encrypt("123456"));
        //63a9f0ea7bb98050796b649e85481845
        System.out.println(MD5.encrypt("root"));
    }
    @Test
    public void testStringHasLength(){
        System.out.println(StringUtils.hasLength(""));
    }
    @Test
    public void testRedis(){
        //(phone,code,5, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set("test","1235435",5,TimeUnit.MINUTES);
        String redisCode = redisTemplate.opsForValue().get("test");
        System.out.println(redisCode);
    }
    @Test
    public void createData(){
        Goods goods=new Goods();
        goods.setGoodsName("小米");
        goods.setGoodsIntro("gfaggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg");
        goods.setGoodsCoverImg("../../../static/images/0a592388-1535-4f9f-8201-ecb78c48bb3d.jpg");
        goods.setOriginalPrice(1000);
        goods.setSellingPrice(100);
        goods.setCreateUser(1);
        goods.setGoodsDetailContent("商品详情");
        goodsMapper.insert(goods);
    }
}
