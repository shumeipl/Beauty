package com.pengling.beauty.service;

import com.pengling.beauty.entity.GoodInOrderInfo;
import com.pengling.beauty.entity.OrderInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderInfoServiceInt {
    List<GoodInOrderInfo> query(int goodId, int userId);
    List<OrderInfo> getOrderInfo(String orderHao, String userId);
    List<String> getOrderHao(Integer orderStatus,Integer userId);
    List<String> getAllOrderHao(Integer userId);
}
