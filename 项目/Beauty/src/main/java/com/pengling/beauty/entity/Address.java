package com.pengling.beauty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class Address {
    private Integer userId;
//   收货人姓名
    private String addressConsignee;
    private String addressTel;
    private String detailAddress;
    private int addressTag;
    private int addressDefault;
    private int  addressId;
    private String countryName;
    private String provinceName;
    private String cityName;
    private String districtName;

    public Address(Integer userId, String addressConsignee, String addressTel, String detailAddress, int addressTag, int addressDefault, String countryName, String provinceName, String cityName, String districtName) {
        this.userId = userId;
        this.addressConsignee = addressConsignee;
        this.addressTel = addressTel;
        this.detailAddress = detailAddress;
        this.addressTag = addressTag;
        this.addressDefault = addressDefault;
        this.countryName = countryName;
        this.provinceName = provinceName;
        this.cityName = cityName;
        this.districtName = districtName;
    }
}
