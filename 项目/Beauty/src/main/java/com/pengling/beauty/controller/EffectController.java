package com.pengling.beauty.controller;

import com.alibaba.fastjson.JSONArray;
import com.pengling.beauty.service.EffectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EffectController {
    @Autowired
    private EffectService effectService;
    @GetMapping("GET/Effect")
    @ResponseBody
    public String getAllEffect(){
        return JSONArray.toJSONString(effectService.getAllEffect());
    }
}
