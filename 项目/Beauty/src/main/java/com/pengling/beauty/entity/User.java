package com.pengling.beauty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Integer userId;
    private String userNickname;
    private String userAge;
    private String userGender;
    private String userOpenId;
    private String userAvatar;
    private String userTel;
    private String userCode;
    private Integer userPoints;
    private String userDate;

    public User(Integer userId, Integer userPoints) {
        this.userId = userId;
        this.userPoints = userPoints;
    }

    public User(String userNickname, String userGender, String userOpenId, String userAvatar) {
        this.userNickname = userNickname;
        this.userGender = userGender;
        this.userOpenId = userOpenId;
        this.userAvatar = userAvatar;
    }
}
