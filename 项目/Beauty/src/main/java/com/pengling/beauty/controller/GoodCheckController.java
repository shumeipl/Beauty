package com.pengling.beauty.controller;

import com.alibaba.fastjson.JSONArray;
import com.pengling.beauty.entity.Good;
import com.pengling.beauty.entity.Page;
import com.pengling.beauty.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Component
public class GoodCheckController {
    @Autowired
    private GoodService goodService;
    @RequestMapping("GET/GoodCheck/{currentPage}")
    @ResponseBody
    public String showAdminForward(@PathVariable("currentPage") String currentPage ){
        System.out.println("展示已上架商品信息。。");
        Page<Good> goodPage = goodService.selectAllReadyGood(Integer.parseInt(currentPage));
        System.out.println(goodPage);
        return JSONArray.toJSONString(goodPage);
    }
    @PostMapping("DELETE/GoodCheck")
    @ResponseBody
    public String deleteGood(HttpServletRequest request){
        System.out.println("删除商品。。");
        int goodID = Integer.parseInt(request.getParameter("goodId"));
        int status = 0 ;
        status = goodService.refuseReadyGood(goodID);
        return JSONArray.toJSONString(status);
    }
    @PostMapping("PATCH/GoodCheck")
    @ResponseBody
    public String updateGoodDetail(HttpServletRequest request){
        String path = request.getParameter("path");
        Integer goodId= Integer.valueOf(request.getParameter("goodId"));
        return JSONArray.toJSONString(goodService.updateDetail(path,goodId));
    }
    @PostMapping("PUT/GoodCheck")
    @ResponseBody
    public String acceptGood(HttpServletRequest request){
        System.out.println("删除商品。。");
        int goodID = Integer.parseInt(request.getParameter("goodId"));
        int status = 0 ;
        status = goodService.acceptReadyGood(goodID);
        return JSONArray.toJSONString(status);
    }
    @PostMapping("PATCH/RefuseGoodCheck")
    @ResponseBody
    public String refuseGood(HttpServletRequest request){
        System.out.println("删除商品。。");
        int goodID = Integer.parseInt(request.getParameter("goodId"));
        int status = 0 ;
        status = goodService.refuseAlreadyGood(goodID);
        return JSONArray.toJSONString(status);
    }
    @GetMapping ("GET/GoodCheckByName/{goodId}")
    @ResponseBody
    public String getGoodById(@PathVariable("goodId") String goodId ){
        System.out.println("搜索商品。。");
        List<Good> goodList = goodService.selectByIdGood(Integer.parseInt(goodId));
        return JSONArray.toJSONString(goodList);
    }
    @PostMapping("PATCH/GoodDetail")
    @ResponseBody
    public String updateGoodDetail1(HttpServletRequest request){
        String goodDetail = request.getParameter("goodDetail");
        String goodId = request.getParameter("goodId");
        System.out.println("搜索商品。。");
        int status = goodService.updateDetail(goodDetail, Integer.valueOf(goodId));
        return JSONArray.toJSONString(status);
    }
}
