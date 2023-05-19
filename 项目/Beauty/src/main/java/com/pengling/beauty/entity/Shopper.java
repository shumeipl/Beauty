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
public class Shopper {
    private Integer shopperId;
    private String shopperAvatar;
    private String shopperName;
    private Integer shopperCode;
    private String shopperTel;
    private String shopperPassword;
    private String shopperIdCard;
    private String shopperAddress;
    private String shopperDescribe;
    private Date shopperRegistDate;

    public Shopper(Integer shopperId, String shopperPassword) {
        this.shopperId = shopperId;
        this.shopperPassword = shopperPassword;
    }

    public Shopper(String shopperTel, String shopperPassword){
        this.shopperTel = shopperTel;
        this.shopperPassword = shopperPassword;
    }

    public Shopper(String shopperAvatar, String shopperName, String shopperTel, String shopperPassword, String shopperIdCard, String shopperAddress, String shopperDescribe, Date shopperRegistDate) {
        this.shopperAvatar = shopperAvatar;
        this.shopperName = shopperName;
        this.shopperTel = shopperTel;
        this.shopperPassword = shopperPassword;
        this.shopperIdCard = shopperIdCard;
        this.shopperAddress = shopperAddress;
        this.shopperDescribe = shopperDescribe;
        this.shopperRegistDate = shopperRegistDate;
    }
}
