package com.pengling.beauty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class GoodInOrderInfo {
//    order表和good表联查
    private String goodImage1;
    private Integer goodId;
    private String goodName;
    private Integer goodNum;
    private Double goodPrice;
    private Integer shopperId;
    private String feature;
}
