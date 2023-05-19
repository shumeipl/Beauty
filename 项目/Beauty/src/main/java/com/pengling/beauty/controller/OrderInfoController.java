package com.pengling.beauty.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.pengling.beauty.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;
    @GetMapping("GET/OrderInfo/{userId}/{orderStatus}")
    @ResponseBody
    public String getOrderInfo(@PathVariable("userId") String userId, @PathVariable("orderStatus") String orderStatus){
        return JSONArray.toJSONString(orderInfoService.getOrderInfo(Integer.valueOf(orderStatus),Integer.valueOf(userId)));
    }
    @GetMapping("GET/OrderInfo/{userId}")
    @ResponseBody
    public String getAllOrderInfo(@PathVariable("userId") String userId){
        return JSONArray.toJSONString(orderInfoService.getOrderInfo(Integer.valueOf(userId)));
    }

}
