package com.pengling.beauty.service;

import com.pengling.beauty.entity.Good;
import com.pengling.beauty.entity.Page;

import java.util.List;

public interface GoodServiceInt {
    Page<Good> selectAllReadyGood(int currentPage);
    int acceptReadyGood(int goodId);
    int refuseReadyGood(int goodId);
    //    上架  下架 查询所有 根据id查询 alreadGood
    Page<Good> selectAllAlreadyGood(int currentPage);
    int refuseAlreadyGood(int goodId);
    List<Good> selectByIdAlreadyGood(int goodId);

    //    商家
//    查看商品分组
    Page<Good> selectAll(int currentPage,int ShopperId);
    //    新增商品
    int insert(Good good);
    int selectId(Integer shopperId,String goodName);
    //    上架商品
    int shangJiaGood(int goodId);
    //    下架商品
    int xiaJiaGood(int goodId);
    List<Good> selectByIdGood(int goodId);
    List<Good> selectByCategory(int CategoryId);
    Page<Good> selectByGoodSelected(int pageNum,int pageSize);
    int deleteGoodByGoodId(Integer goodId);
    List<Good> findGoodByFeature(String feature);
    //    搜索商品
    List<Good> findGoodByName(String name);
    int updateDetail(String path,Integer goodId);
    List<String> getMaterial(Integer goodId);
    List<Good> getHotSale();
//    商家
    Page<Good> queryRoost(int currentPage,int shopperId);
    Page<Good> queryChecking(int currentPage,int shopperId);
    Page<Good> queryRefuse(int currentPage,int shopperId);
    int becomeSelected(int goodId);

    List<Good> selectAllSupplement(int currentPage,int shopperId);
}
