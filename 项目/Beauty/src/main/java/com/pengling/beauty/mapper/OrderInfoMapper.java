package com.pengling.beauty.mapper;

import com.pengling.beauty.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderInfoMapper {
    List<OrderInfo> getOrderInfo(String orderHao);
}
