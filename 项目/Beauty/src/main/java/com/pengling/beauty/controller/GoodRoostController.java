package com.pengling.beauty.controller;

import com.alibaba.fastjson.JSONArray;
import com.pengling.beauty.entity.Admin;
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
public class GoodRoostController {
    @Autowired
    private GoodService goodService;
    @RequestMapping("GET/GoodRoost/{id}")
    @ResponseBody
    public String showAdminForward(@PathVariable("id") String id ){
        System.out.println("展示已上架商品信息。。");
       List<Good> goodList = goodService.selectByIdAlreadyGood(Integer.parseInt(id));
        return JSONArray.toJSONString(goodList);
    }
    @RequestMapping("GET/goodsSupplement/{currentPage}/{shopperId}")
    @ResponseBody
    public String getGoodSupplement(@PathVariable("currentPage") String currentPage,@PathVariable("shopperId") String shopperId ){
        List<Good> goodList = goodService.selectAllSupplement(Integer.parseInt(currentPage), Integer.parseInt(shopperId));
        return JSONArray.toJSONString(goodList);
    }
    @PostMapping("DELETE/GoodRoost")
    @ResponseBody
    public String deleteGood(HttpServletRequest request){
        System.out.println("删除商品。。");
        int goodID = Integer.parseInt(request.getParameter("goodId"));
        int status = 0 ;
        status = goodService.xiaJiaGood(goodID);
        return JSONArray.toJSONString(status);
    }
    @GetMapping("GET/GoodRoostById/{goodId}")
    @ResponseBody
    public String getGoodById(@PathVariable("goodId") String goodId ){
        System.out.println("搜索商品。。");
        List<Good> goodList = goodService.selectByIdGood(Integer.parseInt(goodId));
        return JSONArray.toJSONString(goodList);
    }
    @GetMapping("GET/GoodBySelected/{pageNum}/{pageSize}")
    @ResponseBody
    public String getGoodBySelected(@PathVariable("pageNum") String pageNum,@PathVariable("pageSize") String pageSize ){
        System.out.println("搜索商品。。");
        Page<Good> goodPage = goodService.selectByGoodSelected(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
        return JSONArray.toJSONString(goodPage);
    }
    @GetMapping("GET/GoodByFeature/{feature}")
    @ResponseBody
    public String getGoodByFeature(@PathVariable("feature") String feature ){
        System.out.println("通过特征搜索商品。。");
        List<Good> goodList = goodService.findGoodByFeature(feature);
        return JSONArray.toJSONString(goodList);
    }
    @GetMapping("GET/GoodByName/{name}")
    @ResponseBody
    public String getGoodByName(@PathVariable("name") String name ){
        System.out.println("通过商品名搜索商品。。");
        System.out.println(name);
        List<Good> goodList = goodService.findGoodByName(name);
        return JSONArray.toJSONString(goodList);
    }
    @GetMapping("GET/getHotGood")
    @ResponseBody
    public String getHotGood(){
        return JSONArray.toJSONString(goodService.getHotSale());
    }
    @PostMapping("PATCH/becomeSelected")
    @ResponseBody
    public String becomeSelected(HttpServletRequest request){
        System.out.println("被挑选为精选");
        int goodID = Integer.parseInt(request.getParameter("goodId"));
        int status = 0 ;
        status = goodService.becomeSelected(goodID);
        return JSONArray.toJSONString(status);
    }
}
