package com.pengling.beauty.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pengling.beauty.entity.Admin;
import com.pengling.beauty.entity.Shopper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopperMapper extends BaseMapper<Shopper> {
    List<Shopper> login(Shopper shopper);
    List<Shopper> queryAllShoppersByASC(int startNum,int endNum);
    List<Shopper> queryAllShoppersByDESC(int startNum,int endNum);
    int deleteByShopperOpenid(Integer id);
    int updateCode(int code ,int shopperTel,int shopperId);
    int updateDeleteCode(int shopperTel,int shopperId);
    List<Shopper> queryAllByShopperOpenidShoppers(Integer id);
    List<Shopper> queryAllByShopperNameShoppers(String name);
    int ShopperCount();
//
    int updateShopperById(Shopper shopper);
    int insertShopperById(Shopper shopper);
    int updateShopperPasswordById(Shopper shopper);
//    通过shopperId查找商家名
    String findShopperName(String shopperId);
}

