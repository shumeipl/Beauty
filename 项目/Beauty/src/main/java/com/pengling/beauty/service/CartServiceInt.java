package com.pengling.beauty.service;

import com.pengling.beauty.entity.Cart;

import java.util.Date;
import java.util.List;

public interface CartServiceInt {
//    用户新增商品到购物车
   Integer addGoodToCart(Cart cart);
   Integer descGoodNum(int userId,int goodId);
   Integer deleteGoodNum(int userId,int goodId);
   Integer updateGoodNum(int userId,int goodId,int goodNum);
   Integer updateCart(int allSelected, int selected, int goodNum, Date date, int userId, int goodId);

}
