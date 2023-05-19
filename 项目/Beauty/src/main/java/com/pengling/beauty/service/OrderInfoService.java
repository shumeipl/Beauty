package com.pengling.beauty.service;

import com.pengling.beauty.entity.GoodInOrderInfo;
import com.pengling.beauty.entity.OrderInfo;
import com.pengling.beauty.mapper.GoodInOrderInfoMapper;
import com.pengling.beauty.mapper.OrderInfoMapper;
import com.sun.org.apache.xml.internal.utils.res.XResources_zh_TW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderInfoService implements OrderInfoServiceInt{
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private GoodInOrderInfoMapper goodInOrderInfoMapper;
    @Override
    public List<GoodInOrderInfo> query(int goodId, int userId) {
        return  goodInOrderInfoMapper.queryAll(goodId,userId);
    }

    @Override
    public List<OrderInfo> getOrderInfo(String orderHao, String userId) {
//        只有一个
        return orderInfoMapper.getOrderInfo(orderHao);
    }

    @Override
    public List<String> getOrderHao(Integer orderStatus, Integer userId) {
        return orderService.getOrderHao(orderStatus,userId);
    }

    @Override
    public List<String> getAllOrderHao(Integer userId) {
        return orderService.getOrderHao(userId);
    }



    public List<OrderInfo> getOrderInfo(Integer orderStatus,Integer userId){
        List<OrderInfo> orderInfos = new ArrayList<>();
        List<String> orderHaos = getOrderHao(orderStatus,userId);
        System.out.println(orderHaos);
        if (orderHaos.size()>0) {
            for (String s : orderHaos) {
                OrderInfo order = new OrderInfo();
                order.setOrderHao(s);
                List<Integer> goodIds = orderService.selectByOrderHao(s);
                OrderInfo orderInfo = orderInfoMapper.getOrderInfo(s).get(0);
                List<GoodInOrderInfo> goodInOrderInfoList = new ArrayList<>();
                for (Integer i : goodIds) {
                    System.out.println(i);
                    System.out.println(goodInOrderInfoMapper.query(i,userId,s));
                    GoodInOrderInfo goodInOrderInfo = goodInOrderInfoMapper.query(i,userId,s).get(0);
                    goodInOrderInfoList.add(goodInOrderInfo);
                }
                order.setOrderStatus(orderStatus);
                order.setGoodInfo(goodInOrderInfoList);
                order.setOrderPrice(orderInfo.getOrderPrice());
                order.setOrderDate(orderInfo.getOrderDate());
                order.setOrderTrackingNum(orderInfo.getOrderTrackingNum());
                order.setOrderTrackingShopper(orderInfo.getOrderTrackingShopper());
                System.out.println("时间为"+orderInfo.getOrderDate());
                orderInfos.add(order);
            }
        }
        return orderInfos ;
    }

    public List<OrderInfo> getOrderInfo(Integer userId){
        List<OrderInfo> orderInfos = new ArrayList<>();
        List<String> orderHaos = getAllOrderHao(userId);
        System.out.println(orderHaos);
        if (orderHaos.size()>0) {
            for (String s : orderHaos) {
                OrderInfo order = new OrderInfo();
                List<Integer> goodIds = orderService.selectByOrderHao(s);
                OrderInfo orderInfo = orderInfoMapper.getOrderInfo(s).get(0);
                List<GoodInOrderInfo> goodInOrderInfoList = new ArrayList<>();
                int orderStatus= orderService.getOrderStatus(s).get(0);
                for (Integer i : goodIds) {
                    if (goodInOrderInfoMapper.query(i, userId,s).size()==0){
                        continue;
                    }
                    GoodInOrderInfo goodInOrderInfo = goodInOrderInfoMapper.query(i, userId,s).get(0);
                    System.out.println(goodInOrderInfo);
                    goodInOrderInfoList.add(goodInOrderInfo);
                }
                if (goodInOrderInfoList.isEmpty()){
                    continue;
                }
                orderInfo.setOrderStatus(orderStatus);
                order.setOrderHao(s);
                order.setOrderStatus(orderStatus);
                order.setGoodInfo(goodInOrderInfoList);
                order.setOrderPrice(orderInfo.getOrderPrice());
                orderInfos.add(order);
            }
        }
        return orderInfos ;
    }
}
