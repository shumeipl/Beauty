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
public class Cooperation {
    private Integer id;
    private Integer userId;
    private String name;
    private String idcard;
    private String company;
    private String duty;
    private Integer phone;
    private String address;
    private String date;
    private String companyAddress;
    private String department;

    public Cooperation(Integer userId, String name, String idcard, String company, String duty, Integer phone, String address, String date, String companyAddress) {
        this.userId = userId;
        this.name = name;
        this.idcard = idcard;
        this.company = company;
        this.duty = duty;
        this.phone = phone;
        this.address = address;
        this.date = date;
        this.companyAddress = companyAddress;
    }

    public Cooperation(Integer userId, String name, String company, String duty, Integer phone, String date, String companyAddress,String department) {
        this.userId = userId;
        this.name = name;
        this.company = company;
        this.duty = duty;
        this.phone = phone;
        this.date = date;
        this.companyAddress = companyAddress;
        this.department=department;
    }
}
