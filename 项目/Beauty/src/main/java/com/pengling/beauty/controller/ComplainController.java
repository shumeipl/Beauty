package com.pengling.beauty.controller;

import com.alibaba.fastjson.JSONArray;
import com.pengling.beauty.entity.Complain;
import com.pengling.beauty.entity.Page;
import com.pengling.beauty.service.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ComplainController {
    @Autowired
    private ComplainService complainService;
    @RequestMapping("GET/Complain/{currentPage}")
    @ResponseBody
    public String getComplain(@PathVariable("currentPage") String currentPage){
        Page<Complain> complainPage = complainService.selectAll(Integer.valueOf(currentPage));
        return JSONArray.toJSONString(complainPage);
    }
    @RequestMapping("GET/ComplainByGood/{name}/{currentPage}")
    @ResponseBody
    public String getComplainByGood(@PathVariable("name") String name,@PathVariable("currentPage") String currentPage){
        Page<Complain> complainPage = new Page<>();
        try{
            Integer goodId = Integer.valueOf(name);
            complainPage = complainService.selectByGoodId(Integer.valueOf(currentPage),goodId);
        }
        catch (Exception e){
            complainPage = complainService.selectByGoodName(Integer.valueOf(currentPage),name);
        }
        return JSONArray.toJSONString(complainPage);
    }
}
