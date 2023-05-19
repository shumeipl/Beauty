package com.pengling.beauty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class Cart_goodInfo {
    private Integer goodId;
    private Integer selected;
    private String goodImage1;
    private Double goodPrice;
    private Integer goodStorage;
    private String cartDate;
    private String goodName;
    private Integer goodNum;
}
