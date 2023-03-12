package com.example.mall.pojo.vo;

import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;
import com.sun.xml.internal.ws.api.addressing.OneWayFeature;
import lombok.Data;

import javax.jws.Oneway;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: tqn
 * @since: 2023/3/6 21:45
 * @description:
 */
@Data
public class TypeVO {
    private Long value;
    private String label;
    private List<ChildrenVO> children=new ArrayList<>();
}
