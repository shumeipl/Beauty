package com.pengling.beauty.mapper;

import com.pengling.beauty.entity.Cart_goodInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Cart_goodInfoMapper {
//    根据用户id和商品id查找goodid
    List<Integer> getGoodId(Integer userId,Integer shopperId);
//    根据goodid查找商品信息
    List<Cart_goodInfo> getGoodInfo(Integer goodId);
}
