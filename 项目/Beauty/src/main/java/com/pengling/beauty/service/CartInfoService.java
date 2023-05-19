package com.pengling.beauty.service;

import com.pengling.beauty.entity.CartInfo;
import com.pengling.beauty.entity.Cart_goodInfo;
import com.pengling.beauty.entity.Cart_shopperInfo;
import com.pengling.beauty.mapper.Cart_goodInfoMapper;
import com.pengling.beauty.mapper.Cart_shopperInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartInfoService implements CartInfoServiceInt{
    @Autowired
    private Cart_shopperInfoMapper cart_shopperInfoMapper;
    @Autowired
    private Cart_goodInfoMapper cart_goodInfoMapper;
    @Override
    public List<CartInfo> getCartInfo(Integer userId) {
        List<CartInfo> cartInfos=new ArrayList<>();
        List<Integer> shopperIds =cart_shopperInfoMapper.getShopperIdByUserId(userId);
        for(int shopperId:shopperIds){
           Cart_shopperInfo cart_shopperInfo =  cart_shopperInfoMapper.getShopperInfo(shopperId).get(0);
           List<Integer> goodIds =cart_goodInfoMapper.getGoodId(userId,cart_shopperInfo.getShopperId());
           List<Cart_goodInfo> goodInfos=new ArrayList<>();
           for(int goodId:goodIds){
              Cart_goodInfo goodInfo = cart_goodInfoMapper.getGoodInfo(goodId).get(0);
              goodInfos.add(goodInfo);
           }
           CartInfo cartInfo = new CartInfo(shopperId, cart_shopperInfo.getShopperName(), cart_shopperInfo.getAllSelected(),goodInfos);
           cartInfos.add(cartInfo);
        }
        return cartInfos;
    }
}
