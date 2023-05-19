package com.pengling.beauty.controller;

import com.alibaba.fastjson.JSONArray;
import com.pengling.beauty.entity.Comment;
import com.pengling.beauty.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
//    查找所有评价 日期正序
    @GetMapping("GET/Comment/{currentPage}/{shopperId}")
    @ResponseBody
    public String findAllCommentASC(@PathVariable("currentPage") String currentPage, @PathVariable("shopperId") String shopperId){
        return JSONArray.toJSONString(commentService.selectAllByShopperId(Integer.valueOf(currentPage),Integer.valueOf(shopperId)));
    }

//    写入评价信息
    @PostMapping("POST/Comment")
    @ResponseBody
    public String generateComment(HttpServletRequest request){
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Integer goodId = Integer.valueOf(request.getParameter("goodId"));
        Integer commentLevel = Integer.valueOf(request.getParameter("commentLevel"));
        System.out.println(commentLevel);
        String commentContent = request.getParameter("commentContent");
        String orderHao = request.getParameter("orderHao");
        Integer commentWay = Integer.valueOf(request.getParameter("commentWay"));
        String date = String.valueOf(System.currentTimeMillis());
        return JSONArray.toJSONString(commentService.generateComment(new Comment(userId,goodId,commentLevel,commentContent,0,orderHao,commentWay,date)));
    }
    @GetMapping("GET/getAllComment/{goodid}")
    @ResponseBody
    public String getAllComment(@PathVariable("goodid") String goodid){
        Integer goodId = Integer.valueOf(goodid);
        return JSONArray.toJSONString(commentService.getAllComment(goodId));
    }
    @GetMapping("GET/getNewestComment/{goodid}")
    @ResponseBody
    public String getNewestComment(@PathVariable("goodid") String goodid){
        Integer goodId = Integer.valueOf(goodid);
        return JSONArray.toJSONString(commentService.getNewestComment(goodId));
    }
    @GetMapping("GET/getGreatComment/{goodid}")
    @ResponseBody
    public String getGreatComment(@PathVariable("goodid") String goodid){
        Integer goodId = Integer.valueOf(goodid);
        return JSONArray.toJSONString(commentService.getGreatComment(goodId));
    }
    @GetMapping("GET/getMiddleComment/{goodid}")
    @ResponseBody
    public String getMiddleComment(@PathVariable("goodid") String goodid){
        Integer goodId = Integer.valueOf(goodid);
        return JSONArray.toJSONString(commentService.getMiddleComment(goodId));
    }
    @GetMapping("GET/getBadComment/{goodid}")
    @ResponseBody
    public String getBadComment(@PathVariable("goodid") String goodid){
        Integer goodId = Integer.valueOf(goodid);
        return JSONArray.toJSONString(commentService.getBadComment(goodId));
    }
    @GetMapping("GET/getCountofComment/{goodId}")
    @ResponseBody
    public String getCount(@PathVariable("goodId") String goodId){
        return JSONArray.toJSONString(commentService.getCount(Integer.valueOf(goodId)));
    }
    @GetMapping("GET/getGreatRate/{goodId}")
    @ResponseBody
    public String getGreatRate(@PathVariable("goodId") String goodId){
        int greatCount=commentService.getGreatCount(Integer.valueOf(goodId));
        int allCount=commentService.getCount(Integer.valueOf(goodId));
        Double res = (double)greatCount/(double) allCount;
        res*=100;
        return JSONArray.toJSONString(res);
    }
    @GetMapping("GET/getGreatCount/{goodId}")
    @ResponseBody
    public String getGreatCount(@PathVariable("goodId") String goodId){
        return JSONArray.toJSONString(commentService.getGreatCount(Integer.valueOf(goodId)));
    }
    @GetMapping("GET/getMiddleCount/{goodId}")
    @ResponseBody
    public String getMiddleCount(@PathVariable("goodId") String goodId){
        return JSONArray.toJSONString(commentService.getMiddleCount(Integer.valueOf(goodId)));
    }
    @GetMapping("GET/getBadCount/{goodId}")
    @ResponseBody
    public String getBadCount(@PathVariable("goodId") String goodId){
        return JSONArray.toJSONString(commentService.getBadCount(Integer.valueOf(goodId)));
    }
}
