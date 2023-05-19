package com.pengling.beauty.mapper;

import com.pengling.beauty.entity.Good;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
@Mapper
public interface GoodMapper {
//    未上架  上架 拒绝上架 查询所有 readyGood
    List<Good> selectAllReadyGood(int startNum,int endNum);
    int acceptReadyGood(int goodId);
    int refuseReadyGood(int goodId);
    int countReadyGood();
//    上架  下架 查询所有 根据id查询 alreadGood
    List<Good> selectAllAlreadyGood(int startNum,int endNum);
    int refuseAlreadyGood(int goodId);
    List<Good> selectByIdAlreadyGood(int goodId);
    int countAlreadyGood();
    int selectId(Integer shopperId,String goodName);
//    商家
//    查看商品分组
    List<Good> selectAll(int startNum,int endNum,int shopperId);
//    新增商品
    int insert(Good good);
    int countAll();
//    上架商品
    int shangJiaGood(int goodId);
//    下架商品
    int xiaJiaGood(int goodId);
    List<Good> selectByIdGood(int goodId);

//    根据类型查找所有商品
    List<Good> selectByCategory(Integer smallCategory);
    List<Good> selectByGoodSelected(int PageNum,int PageSize);
    int countSelectByGoodSelected();
//    删除商品
    int deleteGoodByGoodId(Integer goodId);
//    查找功效商品
    List<Good> findGoodByFeature(String feature);
//    搜索商品
    List<Good> findGoodByName(String name);
    int updateDetail(String path,Integer goodId);
    List<String> getMaterial(Integer goodId);
//    得到热销榜前5位 根据销售的数量排序   时间段（本周？）
    List<Good> getHotSale(String date);
//    减少商品库存 加销量
    int decrGoodStorage(int num,int goodId);
    int addGoodSoldNum(int num,int goodId);

//    取消订单/超时未支付
    int addGoodStorage(int num,int goodId);
    int decrGoodSoldNum(int num,int goodId);
//    找到某商家 已上架、待审核、拒绝上架的商品
    List<Good> queryRoost(int shopperId,int startNum,int endNum);
    List<Good> queryChecking(int shopperId,int startNum,int endNum);
    List<Good> queryRefuse(int shopperId,int startNum,int endNum);
//    成为精选
    int becomeSelected(int goodId);
    List<Good> selectAllSupplement(int shopperId,int startNum,int endNum);
}
