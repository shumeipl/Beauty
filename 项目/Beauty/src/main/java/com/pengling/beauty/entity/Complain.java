package com.pengling.beauty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Complain {
    private Integer complainId;
    private Integer goodId;
    private Integer userId;
    private String goodName;
    private String complainContent;
    private String shopperTel;
    private Date complainDate;

    public Complain(Integer goodId, Integer userId, String complainContent, Date complainDate) {
        this.goodId = goodId;
        this.userId=userId;
        this.goodName = goodName;
        this.complainContent = complainContent;
        this.shopperTel = shopperTel;
        this.complainDate = complainDate;
    }
}
