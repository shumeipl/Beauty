package com.pengling.beauty.service;

import com.pengling.beauty.entity.Score;
import com.pengling.beauty.mapper.ScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService implements ScoreServiceInt{
    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public int updateScore(int goodId, int addScore) {
        return scoreMapper.updateScore(goodId,addScore);
    }

    @Override
    public List<Score> hasGoodId(int goodId) {
        return scoreMapper.hasGoodId(goodId);
    }

    @Override
    public int updateAvgScore(double avgScore, int goodId) {
        return scoreMapper.updateAvgScore(avgScore,goodId);
    }

    @Override
    public int insertScore(Score score) {
        return scoreMapper.insertScore(score);
    }

    @Override
    public List<Score> selectTopTen(String feature) {
        return scoreMapper.selectTopTen(feature);
    }
}
