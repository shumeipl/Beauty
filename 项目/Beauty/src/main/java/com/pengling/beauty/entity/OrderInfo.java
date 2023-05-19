package com.pengling.beauty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Repository
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderInfo {
    private Integer orderId;
    private String orderHao;
    private Double orderPrice;
    private String orderDate;
    private List<GoodInOrderInfo> goodInfo = new ArrayList<>();
    private Integer orderStatus;
    private String orderTrackingShopper;
    private Integer orderTrackingNum;

}
