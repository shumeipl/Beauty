package com.pengling.beauty.service;

import com.pengling.beauty.entity.Good;
import com.pengling.beauty.entity.Order;
import com.pengling.beauty.entity.Page;
import com.pengling.beauty.mapper.GoodMapper;
import com.pengling.beauty.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService implements OrderServiceInt{
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public Page<Order> selectAll(int currentPage) {
        int startNum=(currentPage-1)*10;
        int endNum = 10;
        int count = orderMapper.count();
        int pageCount =count%10==0?count/10:count/10+1;
        Page<Order> orderPage = new Page<>(pageCount,currentPage,orderMapper.selectAll(startNum,endNum));
        return orderPage;
    }
    @Override
    public List<Order> selectByOrderId(Integer orderId) {
        return orderMapper.selectByOrderId(orderId);
    }

    @Override
    public Page<Order> selectByOrderAfterSH(int currentPage) {
        int startNum=(currentPage-1)*10;
        int endNum = 10;
        int count = orderMapper.selectByOrderAfterSHCount();
        int pageCount =count%10==0?count/10:count/10+1;
        Page<Order> orderPage = new Page<>(pageCount,currentPage,orderMapper.selectByOrderAfterSH(startNum,endNum));
        return orderPage;
    }

    @Override
    public Page<Order> selectByOrderBeforeSH(int currentPage) {
        int startNum=(currentPage-1)*10;
        int endNum = 10;
        int count = orderMapper.selectByOrderBeforeSHCount();
        int pageCount =count%10==0?count/10:count/10+1;
        Page<Order> orderPage = new Page<>(pageCount,currentPage,orderMapper.selectByOrderBeforeSH(startNum,endNum));
        return orderPage;
    }

    @Override
    public Page<Order> selectByOrderBeforeFH(int currentPage) {
        int startNum=(currentPage-1)*10;
        int endNum = 10;
        int count = orderMapper.selectByOrderBeforeFHCount();
        int pageCount =count%10==0?count/10:count/10+1;
        Page<Order> orderPage = new Page<>(pageCount,currentPage,orderMapper.selectByOrderBeforeFH(startNum,endNum));
        return orderPage;
    }

    @Override
    public List<Order> selectByOrderAfterSHByOrderId(int orderId) {
        return orderMapper.selectByOrderAfterSHByOrderId(orderId);
    }

    @Override
    public List<Order> selectByOrderBeforeSHByOrderId(int orderId) {
        return orderMapper.selectByOrderBeforeSHByOrderId(orderId);
    }

    @Override
    public List<Order> selectByOrderBeforeFHByOrderId(int orderId) {
        return orderMapper.selectByOrderBeforeFHByOrderId(orderId);
    }

    @Override
    public int FH(int orderId, int trackingNum,String trackingShopper) {
        return orderMapper.FH(orderId,trackingNum, trackingShopper);
    }

    @Override
    public List<Order> selectByOrderBeforeSH(int i, Integer shopperid) {
        return orderMapper.selectByOrderBeforeSH1((i-1)*10,10,shopperid);
    }
    @Autowired
    private GoodMapper goodMapper;
    @Override
    public int generateOrder(Order order) {
        goodMapper.decrGoodStorage(order.getGoodNum(),order.getGoodId());
        goodMapper.addGoodSoldNum(order.getGoodNum(),order.getGoodId());
        return orderMapper.generateOrder(order);
    }

    @Override
    public List<String> getOrderHao(Integer orderStatus, Integer userId) {
        return orderMapper.getOrderHao(orderStatus,userId);
    }

    @Override
    public List<String> getOrderHao(Integer userId) {
        return orderMapper.getAllOrderHao(userId);
    }

    @Override
    public List<Integer> selectByOrderHao(String orderHao) {
        return orderMapper.selectByOrderHao(orderHao);
    }

    @Override
    public List<Integer> getOrderStatus(String orderHao) {
        return orderMapper.selectOrderStatusByOrderHao(orderHao);
    }

    @Override
    public int changeOrderStatus(String orderHao, int userId,int orderStatus) {
        List<Integer> goodIds = orderMapper.selectByOrderHao(orderHao);
        System.out.println("大小是："+goodIds.size());
        for(Integer i : goodIds){
            int good_Num=orderMapper.selectOrderByHaoAndGoodId(orderHao,i);
            System.out.println(good_Num);
            int i1 = goodMapper.addGoodStorage(good_Num,i);
            int i2 =goodMapper.decrGoodSoldNum(good_Num,i);
            System.out.println("--------------");
            System.out.println("i1="+i1);
            System.out.println("i2="+i2);
            System.out.println("--------------");
        }
        return orderMapper.changeOrderStatus(orderHao,userId,orderStatus);
    }

    @Override
    public int deleteOrder(String orderHao, int userId) {
        List<Integer> goodIds = orderMapper.selectByOrderHao(orderHao);
        for(Integer i : goodIds){
            int good_Num=orderMapper.selectOrderByHaoAndGoodId(orderHao,i);
            System.out.println(good_Num);
            int i1 = goodMapper.addGoodStorage(good_Num,i);
            int i2 =goodMapper.decrGoodSoldNum(good_Num,i);
            System.out.println("i1="+i1);
            System.out.println("i2="+i2);
        }
        return orderMapper.deleteOrder(orderHao,userId);
    }
}
