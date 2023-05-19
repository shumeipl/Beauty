package com.pengling.beauty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoodDetail {
    private Integer goodId;
    private Integer shopperOpenid;
    private String goodName;
    private Double goodPrice;
    private String goodImg1;
    private String goodImg2;
    private String goodImg3;
    private Integer goodStorage;
    private String categoryName;
    private String material;
    private String smallCategoryName;
    private String GoodDetail;
}
