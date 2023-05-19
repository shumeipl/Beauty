package com.pengling.beauty.controller;

import com.alibaba.fastjson.JSONArray;
import com.pengling.beauty.entity.Search;
import com.pengling.beauty.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;
    @RequestMapping("GET/search/{userId}")
    @ResponseBody
    public String getSearch(@PathVariable("userId") String userId){
        System.out.println(userId);
        if (userId==null){
            return null;
        }
        return JSONArray.toJSONString(searchService.getSearchList(Integer.valueOf(userId)));
    }
    @PostMapping("POST/search")
    @ResponseBody
    public String addSearch(HttpServletRequest request){
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        String searchContent = request.getParameter("searchContent");
        String date = String.valueOf(System.currentTimeMillis());
        return JSONArray.toJSONString(searchService.addSearch(new Search(userId,searchContent,date)));
    }
}
