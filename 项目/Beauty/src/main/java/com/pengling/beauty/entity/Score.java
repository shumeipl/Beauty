package com.pengling.beauty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class Score {
    private Integer goodId;
    private String goodFeature;
    private Integer goodAllScore;
    private Integer goodOrderNum;
    private Double goodAvgScore;
    private Good good;

    public Score(Integer goodId, String goodFeature, Integer goodAllScore, Integer goodOrderNum) {
        this.goodId = goodId;
        this.goodFeature = goodFeature;
        this.goodAllScore = goodAllScore;
        this.goodOrderNum = goodOrderNum;
    }
}
