package com.pengling.beauty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Good {
    private Integer goodId;
    private String goodIntro;
    private Integer shopperId;
    private String goodName;
    private Double goodPrice;
    private String goodImg1;
    private String goodImg2;
    private String goodImg3;
    private Integer goodStatus;
    private Integer goodStorage;
    private String categoryName;
    private String smallCategoryName;
    private String goodDetail;
    private String goodCertificationNumber;
    private String material;
    private Integer goodSoldNum;
    private String goodFeature;
    private String goodVideo;
    private String goodSubGoodName;
    private String goodActiveTitle;
//    public Good(String GoodCertificationNumber ,Integer shopperId, String goodName, Double goodPrice, String goodImg1, String goodImg2, String goodImg3, Integer goodStorage, String categoryName, String smallCategoryName, String material,String goodIntro,Integer goodStatus) {
//        this.shopperId = shopperId;
//        this.goodName = goodName;
//        this.goodPrice = goodPrice;
//        this.goodImg1 = goodImg1;
//        this.goodImg2 = goodImg2;
//        this.goodImg3 = goodImg3;
//        this.goodStorage = goodStorage;
//        this.categoryName = categoryName;
//        this.smallCategoryName = smallCategoryName;
//        this.goodDetail = goodDetail;
//        this.material = material;
//        this.goodCertificationNumber=GoodCertificationNumber;
//        this.goodIntro = goodIntro;
//        this.goodStatus=goodStatus;
//    }
//
//    public Good(Integer shopperId, String goodName, Double goodPrice, String goodImg1, String goodImg2, String goodImg3, Integer goodStorage, String categoryName, String smallCategoryName,String goodCertificationNumber, String material, Integer goodSoldNum, String goodVideo,Integer goodStatus) {
//        this.shopperId = shopperId;
//        this.goodName = goodName;
//        this.goodPrice = goodPrice;
//        this.goodImg1 = goodImg1;
//        this.goodImg2 = goodImg2;
//        this.goodImg3 = goodImg3;
//        this.goodStorage = goodStorage;
//        this.categoryName = categoryName;
//        this.smallCategoryName = smallCategoryName;
//        this.goodDetail = goodDetail;
//        this.goodCertificationNumber = goodCertificationNumber;
//        this.material = material;
//        this.goodSoldNum = goodSoldNum;
//        this.goodVideo = goodVideo;
//        this.goodStatus = goodStatus;
//    }
//
//    public Good(Integer shopperId, String goodName, Double goodPrice, String goodImg1, String goodImg2, String goodImg3, Integer goodStorage, String categoryName, String smallCategoryName, String goodDetail, String goodCertificationNumber, String material, Integer goodSoldNum, String goodVideo, String goodSubGoodName) {
//        this.shopperId = shopperId;
//        this.goodName = goodName;
//        this.goodPrice = goodPrice;
//        this.goodImg1 = goodImg1;
//        this.goodImg2 = goodImg2;
//        this.goodImg3 = goodImg3;
//        this.goodStorage = goodStorage;
//        this.categoryName = categoryName;
//        this.smallCategoryName = smallCategoryName;
//        this.goodDetail = goodDetail;
//        this.goodCertificationNumber = goodCertificationNumber;
//        this.material = material;
//        this.goodSoldNum = goodSoldNum;
//        this.goodVideo = goodVideo;
//        this.goodSubGoodName = goodSubGoodName;
//    }
//
//
//    public Good(int shopperId, String goodName, Double goodPrice, String img1, String img2, String img3, Integer goodStorage, String goodCategory, String goodSmallCategory, String goodDetail, String goodCertificationNumber, String goodMaterial, Integer goodSoldNum, String goodVideo, String goodIntroduct, Integer goodStatus) {
//        this.shopperId=shopperId;
//        this.goodName = goodName;
//        this.goodPrice = goodPrice ;
//        this.goodImg1=img1;
//        this.goodImg2=img2;
//        this.goodImg3=img3;
//        this.goodStorage=goodStorage;
//        this.goodIntro=goodIntroduct;
//        this.categoryName =goodCategory;
//        this.smallCategoryName=goodSmallCategory;
//        this.goodDetail =goodDetail;
//        this.goodCertificationNumber=goodCertificationNumber;
//        this.material =goodMaterial;
//        this.goodSoldNum=goodSoldNum;
//        this.goodVideo=goodVideo;
//        this.goodStatus=goodStatus;
//    }

    public Good(int shopperId, String goodName, Double goodPrice, String img1, String img2, String img3, Integer goodStorage, String goodVideo, String goodCategory, String goodSmallCategory, String goodCertificationNumber, String material, Integer goodSoldNum, String goodIntroduct, Integer goodStatus,String goodFeature) {
        this.shopperId=shopperId;
        this.goodName=goodName;
        this.goodPrice=goodPrice;
        this.goodImg1=img1;
        this.goodImg2=img2;
        this.goodImg3=img3;
        this.goodStorage=goodStorage;
        this.goodVideo = goodVideo;
        this.categoryName=goodCategory;
        this.smallCategoryName=goodSmallCategory;
        this.goodCertificationNumber=goodCertificationNumber;
        this.material=material;
        this.goodSoldNum=goodSoldNum;
        this.goodIntro=goodIntroduct;
        this.goodStatus=goodStatus;
        this.goodFeature=goodFeature;
    }

}
