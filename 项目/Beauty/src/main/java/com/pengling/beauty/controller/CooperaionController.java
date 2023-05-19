package com.pengling.beauty.controller;

import com.alibaba.fastjson.JSONArray;
import com.pengling.beauty.entity.Cooperation;
import com.pengling.beauty.service.CooperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CooperaionController {
    @Autowired
    private CooperationService cooperationService;
    @RequestMapping("GET/Cooperations/{currentPage}")
    @ResponseBody
    public String getCooperations(@PathVariable("currentPage") String currentPage){
        return JSONArray.toJSONString(cooperationService.selectAll(Integer.parseInt(currentPage)));
    }
    @PostMapping("PUT/AcceptCooperations")
    @ResponseBody
    public String acceptCooperations(HttpServletRequest request){
        int CooperationId= Integer.parseInt(request.getParameter("cooperationId"));
        return JSONArray.toJSONString(cooperationService.accept(CooperationId));
    }
    @PostMapping("GET/RefuseCooperations")
    @ResponseBody
    public String refuseCooperations(HttpServletRequest request){
        int CooperationId= Integer.parseInt(request.getParameter("cooperationId"));
        return JSONArray.toJSONString(cooperationService.refuse(CooperationId));
    }
    @PostMapping("POST/Cooperation")
    @ResponseBody
    public String addCooperations(HttpServletRequest request){
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        String date = String.valueOf(System.currentTimeMillis());
        System.out.println(date);
        String name = request.getParameter("name");
        String duty = request.getParameter("duty");
        String department = request.getParameter("department");
        String address=request.getParameter("address");
        String company = request.getParameter("company");
        Integer phone = Integer.valueOf(request.getParameter("telphone"));
        Cooperation cooperation = new Cooperation(userId,name,company,duty,phone,date,address,department);
        return JSONArray.toJSONString(cooperationService.insert(cooperation));
    }
}
