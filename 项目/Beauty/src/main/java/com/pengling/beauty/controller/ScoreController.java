package com.pengling.beauty.controller;

import com.alibaba.fastjson.JSONArray;
import com.pengling.beauty.entity.Good;
import com.pengling.beauty.entity.Score;
import com.pengling.beauty.service.GoodService;
import com.pengling.beauty.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ScoreController {
    @Autowired
    private ScoreService scoreService;
//    目的，返回满足功效feature的top10
    @PostMapping("PATCH/Score")
    @ResponseBody
    public String updateScore(HttpServletRequest request){
        String feature  = request.getParameter("feature");
        Integer goodId = Integer.valueOf(request.getParameter("goodId"));
        Integer addScore=Integer.valueOf(request.getParameter("addScore"));
        List<Score> scoreList = scoreService.hasGoodId(goodId);
        int res=0;
        if (scoreList.isEmpty()){
            int r = scoreService.insertScore(new Score(goodId,feature,addScore,1));
            if (r==1){
                res = scoreService.updateAvgScore(addScore,goodId);
            }
        }
        else {
            Double allScore = Double.valueOf(scoreList.get(0).getGoodAllScore());
            int goodNum=scoreList.get(0).getGoodOrderNum();
            Double avgGoodScore=(allScore+addScore)/(goodNum+1);
            scoreService.updateScore(goodId,addScore);
            res =scoreService.updateAvgScore(avgGoodScore,goodId);
        }
       return JSONArray.toJSONString(res);
    }
    @Autowired
    private GoodService goodService;
    @GetMapping("GET/TopTen/{feature}")
    @ResponseBody
    public String getTopTenByFeature(@PathVariable("feature") String feature){
        String Feature=null;
        if (feature.equals("1")){
            Feature="清洁";
        } else if (feature.equals("2")) {
            Feature="保湿补水";
        } else if (feature.equals("3")) {
            Feature="抗氧化";
        } else if (feature.equals("4")) {
            Feature="防晒";
        }
        else if (feature.equals("5")){
            Feature="滋养修护";
        } else if (feature.equals("6")) {
            Feature="淡化痘印";
        }
        List<Score> scoreList = scoreService.selectTopTen(Feature);
        if (!scoreList.isEmpty()) {
            for (Score score : scoreList) {
                System.out.println(score.getGoodId());
                Good good = goodService.selectByIdGood(score.getGoodId()).get(0);
                score.setGood(good);
            }
        }
        else {
            scoreList=null;
        }
        return JSONArray.toJSONString(scoreList);
    }
}
