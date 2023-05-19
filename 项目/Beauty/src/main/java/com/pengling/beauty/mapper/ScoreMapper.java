package com.pengling.beauty.mapper;

import com.pengling.beauty.entity.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScoreMapper {
    int updateScore(int goodId,int addScore);
    List<Score> hasGoodId(int goodId);
    int updateAvgScore(double avgScore,int goodId);
    int insertScore(Score score);
    List<Score> selectTopTen(String feature);
}
