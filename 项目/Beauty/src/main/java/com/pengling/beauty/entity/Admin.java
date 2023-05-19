package com.pengling.beauty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@NoArgsConstructor
@Data
public class Admin {
    private Integer adminId;
    private Integer adminCode;
    private String adminIdcard;
    private String adminPassword;
    private String adminTel;
    private String adminName;
    private String adminSex;
    private String adminAddress;
    public Admin(String adminId,String adminPassword){
        this.adminId = Integer.valueOf(adminId);
        this.adminPassword = adminPassword;
    }
    public Admin(String adminName,String adminIdcard,String adminSex,String adminTel,String adminAddress){
        this.adminName=adminName;
        this.adminIdcard=adminIdcard;
        this.adminSex=adminSex;
        this.adminTel=adminTel;
        this.adminAddress=adminAddress;
    }
    public Admin(Integer adminId,String adminTel){
        this.adminId=adminId;
        this.adminTel = adminTel;
    }
    public Admin(Integer adminId, String adminIdcard, String adminPassword, String adminTel, String adminName, String adminSex, String adminAddress) {
        this.adminId = adminId;
        this.adminIdcard = adminIdcard;
        this.adminPassword = adminPassword;
        this.adminTel = adminTel;
        this.adminName = adminName;
        this.adminSex = adminSex;
        this.adminAddress = adminAddress;
    }
}
