package com.example.mall;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.example.mall.mapper.GoodsMapper;
import com.example.mall.mapper.UserMapper;
import com.example.mall.pojo.Goods;
import com.example.mall.pojo.User;
import com.example.mall.service.AddressService;
import com.example.mall.service.GoodsService;
import com.example.mall.service.OrderService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    @Autowired
    private OrderService orderService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private GoodsService goodsService;
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
        for(int i=0;i<4;i++){
            Goods goods=new Goods();
        goods.setGoodsName("小米");
        goods.setGoodsIntro("gfaggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg");
        goods.setGoodsCoverImg("https://edu-lysa.oss-cn-hangzhou.aliyuncs.com/mall/0a592388-1535-4f9f-8201-ecb78c48bb3d.jpg");
        goods.setOriginalPrice(1000);
        goods.setSellingPrice(100);
        goods.setCreateUser(1L);
        goods.setGoodsDetailContent("商品详情");
        goodsMapper.insert(goods);}
    }
    @Test
    public void testOrder(){
        System.out.println(addressService.query().eq("user_id",1).list());
    }
    @Test
    public void testGoods(){
        List<Long> goodsIdList=new ArrayList<>();
        goodsIdList.add(1L);
        goodsIdList.add(2L);
        System.out.println(goodsService.query().in("goods_id",goodsIdList).list());
    }
    @Test
    public void testSnow(){
        long l = IdUtil.getSnowflake(1, 20).nextId();
        System.out.println(l);
    }@Test
    public void testStream(){
        List<Goods> goodsList=new ArrayList<>();
        for (int i=0;i<10;i++){
            Goods goods=new Goods();
            goods.setSellingPrice(RandomUtil.randomInt(100000));
            goodsList.add(goods);
        }
        goodsList.add(new Goods());
        goodsList.forEach(System.out::println);
        Integer total=goodsList.stream()
                .mapToInt(g->g.getSellingPrice())
                .sum();
        System.out.println(total);
    }
}
