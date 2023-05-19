package com.pengling.beauty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubGood {
    private Integer goodId;
    private Integer subId;
    private String subName;
    private String subImage;
    private Double subPrice;
    private Integer subStorage;
    private Integer subSoldNum;

    public SubGood(Integer goodId, String subName, String subImage, Double subPrice, Integer subStorage) {
        this.goodId = goodId;
        this.subName = subName;
        this.subImage = subImage;
        this.subPrice = subPrice;
        this.subStorage = subStorage;
    }
}
