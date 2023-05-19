package com.pengling.beauty.service;

import com.pengling.beauty.entity.Page;
import com.pengling.beauty.entity.Shopper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShopperServiceInt {
    List<Shopper> login(Shopper shopper);
//    管理员
    Page<Shopper> queryAllShopperByASC(int pageNum);
    Page<Shopper> queryAllShopperByDESC(int pageNum);
    List<Shopper> queryShopperByShopperOpenId(Integer openid);
    List<Shopper> queryShopperByShopperName(String name);
    int deleteShopperByShopperOpenId(Integer openid);
    int shopperCount();
//    商家
    int insertByShopper(Shopper shopper);
    int updateByShopperId(Shopper shopper);
    int updateByPassword(int shopperId,String password);

    int updateCode(int shopperTel,int shopperId) throws Exception;
    int updateDeleteCode(int shopperTel,int shopperId);
}
