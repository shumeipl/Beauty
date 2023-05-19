package com.pengling.beauty.controller;

import com.alibaba.fastjson.JSONArray;
import com.pengling.beauty.entity.smallCategory;
import com.pengling.beauty.service.SmallCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SmallCategoryController {
    @Autowired
    private SmallCategoryService smallCategoryService;
    @RequestMapping("GET/smallCategory/{categoryName}")
    @ResponseBody
    public String getSmallCategoryByCategoryName(@PathVariable("categoryName")String categoryName){
        List<smallCategory> categoryList = smallCategoryService.getSmallCategoryByCategoryName(categoryName);
        return JSONArray.toJSONString(categoryList);
    }
}
