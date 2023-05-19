package com.pengling.beauty.service;

import com.pengling.beauty.entity.Order;
import com.pengling.beauty.entity.Page;

import java.util.List;

public interface OrderServiceInt {
    //    查看所有订单
    Page<Order> selectAll(int currentPage);
    //    根据订单号查询订单
    List<Order> selectByOrderId(Integer orderId);
    Page<Order> selectByOrderAfterSH(int currentPage);
    //    查询待收货
    Page<Order> selectByOrderBeforeSH(int currentPage);
    //    查询待发货
    Page<Order> selectByOrderBeforeFH(int currentPage);
    List<Order> selectByOrderAfterSHByOrderId(int orderId);
    List<Order> selectByOrderBeforeSHByOrderId(int orderId);
    List<Order> selectByOrderBeforeFHByOrderId(int orderId);
    int FH(int orderId,int trackingNum,String trackingShopper);

    List<Order> selectByOrderBeforeSH(int i, Integer shopperid);
    int generateOrder(Order order);
    List<String> getOrderHao(Integer orderStatus,Integer userId);
    List<String> getOrderHao(Integer userId);
    List<Integer> selectByOrderHao(String orderHao);
    List<Integer> getOrderStatus(String orderHao);
    int changeOrderStatus(String orderHao,int userId,int orderStatus);
    int deleteOrder(String orderHao,int userId);
}
