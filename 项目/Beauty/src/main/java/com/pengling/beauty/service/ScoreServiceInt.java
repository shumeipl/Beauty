package com.pengling.beauty.service;

import com.pengling.beauty.entity.Score;

import java.util.List;

public interface ScoreServiceInt {
    int updateScore(int goodId,int addScore);
    List<Score> hasGoodId(int goodId);
    int updateAvgScore(double avgScore,int goodId);
    int insertScore(Score score);
    List<Score> selectTopTen(String feature);
}
