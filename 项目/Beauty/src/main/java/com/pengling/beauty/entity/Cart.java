package com.pengling.beauty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {
//    商家名称，商品ID，商品名称，商品价格，商品库存
    private Integer goodId;
    private Date cartDate;
    private Integer goodNum;
    private Integer selected;
    private Integer userId;
    private Integer shopperId;
    private Integer allSelected;

}
