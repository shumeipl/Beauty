package com.pengling.beauty.mapper;

import com.pengling.beauty.entity.Order;
import com.pengling.beauty.entity.Page;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
//    查看所有订单
    List<Order> selectAll(int startNum,int endNum);
//    根据订单号查询订单
    List<Order> selectByOrderId(Integer orderId);
//    总数
    int count();

//    查询已收货
    List<Order> selectByOrderAfterSH(int startNum,int endNum);
    int selectByOrderAfterSHCount();
    List<Order> selectByOrderAfterSHByOrderId(int orderId);
//    查询待收货
    List<Order> selectByOrderBeforeSH(int startNum,int endNum);
    int selectByOrderBeforeSHCount();
    List<Order> selectByOrderBeforeSHByOrderId(int orderId);
//    查询待发货
    List<Order> selectByOrderBeforeFH(int startNum,int endNum);
    int selectByOrderBeforeFHCount();
    List<Order> selectByOrderBeforeFHByOrderId(int orderId);
    int FH(int orderId,int trackingNum,String trackingShopper);
    List<Order> selectByOrderBeforeSH1(int startNum,int endNum,int shopperId);
    int generateOrder(Order order);
    List<String> getOrderHao(int orderStatus,int userId);
    List<String> getAllOrderHao(int userId);
    List<Integer> selectByOrderHao(String orderHao);
    List<Integer> selectOrderStatusByOrderHao(String orderHao);
    int changeOrderStatus(String orderHao,Integer userId,int orderStatus);
    int deleteOrder(String orderHao,Integer userId);
//    通过orderHao和goodId 得到good_Num
    int selectOrderByHaoAndGoodId(String orderHao,Integer goodId);
}
