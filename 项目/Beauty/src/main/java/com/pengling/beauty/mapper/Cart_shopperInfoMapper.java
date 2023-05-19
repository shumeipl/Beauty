package com.pengling.beauty.mapper;

import com.pengling.beauty.entity.Cart_shopperInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Cart_shopperInfoMapper {
    List<Integer> getShopperIdByUserId(Integer userId);
    List<Cart_shopperInfo> getShopperInfo(Integer shopper_id);
}
