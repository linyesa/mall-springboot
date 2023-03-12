package com.example.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mall.pojo.Type;
import com.example.mall.mapper.TypeMapper;
import com.example.mall.pojo.vo.ChildrenVO;
import com.example.mall.pojo.vo.TypeVO;
import com.example.mall.service.TypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linyes
 * @since 2023-02-26
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

    @Override
    public List<TypeVO> getTypeInfo() {
        QueryWrapper<Type> typeQueryWrapper=new QueryWrapper<>();
        typeQueryWrapper.eq("parent_id",0);
        List<Type> oneTypeLis=baseMapper.selectList(typeQueryWrapper);

        //2 查询所有二级分类  parentid != 0
        QueryWrapper<Type> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<Type> childrenList = baseMapper.selectList(wrapperTwo);

        //创建list集合，用于存储最终封装数据
        List<TypeVO> finalTypeList = new ArrayList<>();
        //3 封装一级分类
        //查询出来所有的一级分类list集合遍历，得到每个一级分类对象，获取每个一级分类对象值，
        //封装到要求的list集合里面 List<OneSubject> finalSubjectList
        for (int i = 0; i < oneTypeLis.size(); i++) { //遍历oneSubjectList集合
            //得到oneSubjectList每个eduSubject对象
            Type type = oneTypeLis.get(i);
            TypeVO typeVO=new TypeVO();
            typeVO.setValue(type.getTypeId());
            typeVO.setLabel(type.getName());

            finalTypeList.add(typeVO);

            //在一级分类循环遍历查询所有的二级分类
            //创建list集合封装每个一级分类的二级分类
            List<ChildrenVO> twoFinalList = new ArrayList<>();
            //遍历二级分类list集合
            for (int m = 0; m < childrenList.size(); m++) {
                //获取每个二级分类
                Type childrenVO = childrenList.get(m);
                //判断二级分类parentid和一级分类id是否一样
                if(childrenVO.getParentId().equals(type.getTypeId())) {
                    ChildrenVO childrenVO1 =new ChildrenVO();
                    childrenVO1.setLabel(childrenVO.getName());
                    childrenVO1.setValue(childrenVO.getTypeId());
                    twoFinalList.add(childrenVO1);
                }
            }
            //把一级下面所有二级分类放到一级分类里面
            typeVO.setChildren(twoFinalList);
        }

        return finalTypeList;
    }
}
