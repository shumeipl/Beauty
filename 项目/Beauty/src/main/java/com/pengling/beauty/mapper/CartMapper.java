package com.pengling.beauty.mapper;

import com.pengling.beauty.entity.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface CartMapper {
    int addGoodToCart(Cart cart);
    List<Cart> selectCartHasGood(int userId,int goodId);
    Integer addGoodNum(int userId, int  goodNum ,int goodId, Date date);
    Integer descGoodNum(int userId,int goodId);
    Integer deleteGoodNum(int userId,int goodId);
    Integer updateGoodNum(int userId,int goodId,int goodNum);
    Integer updateCart(int allSelected,int selected,int goodNum,Date date,int userId,int goodId);
}
