package com.pengling.beauty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer orderId;
    private Integer userId;
    private String addressConsignee;
    private Integer goodId;
    private String orderHao;
    private Integer orderStatus;
    private Integer orderTrackingNum;
    private Integer goodNum;
    private Double orderPrice;
    private String provinceName;
    private String cityName;
    private String districtName;
    private String detailAddress;
    private String orderDate;
    private Integer addressId;
    private String trackingShopper;
    private String goodName;
    private String shopperName;
    private String shopperTel;
    private String addressTel;
    private Integer shopperId;

    public Order(Integer userId, Integer goodId, String orderHao, Integer orderStatus, Integer goodNum, Double orderPrice, Integer addressId,String date,Integer shopperId) {
        this.userId = userId;
        this.goodId = goodId;
        this.orderHao = orderHao;
        this.orderStatus = orderStatus;
        this.goodNum = goodNum;
        this.orderPrice = orderPrice;
        this.addressId = addressId;
        this.orderDate=date;
        this.shopperId=shopperId;
    }

    public Order(Integer userId, Integer goodId, Integer orderStatus, Integer goodNum, Double orderPrice, String orderDate, Integer addressId) {
        this.userId = userId;
        this.goodId = goodId;
        this.orderStatus = orderStatus;
        this.goodNum = goodNum;
        this.orderPrice = orderPrice;
        this.orderDate = orderDate;
        this.addressId = addressId;
    }

}
