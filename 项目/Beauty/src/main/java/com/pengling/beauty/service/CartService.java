package com.pengling.beauty.service;

import com.pengling.beauty.entity.Cart;
import com.pengling.beauty.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CartService implements CartServiceInt{
@Autowired
private CartMapper cartMapper;
    @Override
    public Integer addGoodToCart(Cart cart) {
        Date date = new Date();
        if (cartMapper.selectCartHasGood(cart.getUserId(), cart.getGoodId()).isEmpty()){
        return cartMapper.addGoodToCart(cart);}
        else {
            return cartMapper.addGoodNum(cart.getUserId(),cart.getGoodNum(), cart.getGoodId(),date);
        }
    }

    @Override
    public Integer descGoodNum(int userId, int goodId) {
        return cartMapper.descGoodNum(userId,goodId);
    }

    @Override
    public Integer deleteGoodNum(int userId, int goodId) {
        return cartMapper.deleteGoodNum(userId,goodId);
    }

    @Override
    public Integer updateGoodNum(int userId, int goodId, int goodNum) {
        return cartMapper.updateGoodNum(userId, goodId, goodNum);
    }

    @Override
    public Integer updateCart(int allSelected, int selected, int goodNum, Date date, int userId, int goodId) {
        return cartMapper.updateCart(allSelected,selected,goodNum,date,userId,goodId);
    }
}
