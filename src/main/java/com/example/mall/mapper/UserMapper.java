package com.example.mall.mapper;

import com.example.mall.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author linyes
 * @since 2023-02-26
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
