package com.example.mall.service.impl;

import com.example.mall.pojo.Comment;
import com.example.mall.mapper.CommentMapper;
import com.example.mall.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linyes
 * @since 2022-12-24
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
