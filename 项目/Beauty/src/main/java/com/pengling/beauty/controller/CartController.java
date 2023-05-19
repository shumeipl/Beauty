package com.pengling.beauty.controller;

import com.alibaba.fastjson.JSONArray;
import com.pengling.beauty.entity.Cart;
import com.pengling.beauty.mapper.CartMapper;
import com.pengling.beauty.service.CartInfoService;
import com.pengling.beauty.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartInfoService cartInfoService;
//    新增
    @PostMapping("PUT/GoodToCart")
    @ResponseBody
public String addGoodTOCart(HttpServletRequest request){
    Integer userId= Integer.valueOf(request.getParameter("userId"));
    Integer goodId = Integer.valueOf(request.getParameter("goodId"));
    Integer goodNum= Integer.valueOf(request.getParameter("goodNum"));
    Date date = new Date();
    Integer shopperId= Integer.valueOf(request.getParameter("shopperId"));
    int selected=1;
    int allSelected=0;
    return JSONArray.toJSONString(cartService.addGoodToCart(new Cart(goodId,date,goodNum,selected,userId,shopperId,allSelected)));
}
//查询
    @GetMapping ("GET/Cart/{userId}")
    @ResponseBody
    public String queryCart(@PathVariable("userId") String userId){
    return JSONArray.toJSONString(cartInfoService.getCartInfo(Integer.valueOf(userId)));
    }
//    减一
    @PostMapping ("DELETE/CartGoodNum")
    @ResponseBody
    public String descCartGood(HttpServletRequest request){
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Integer goodId=Integer.valueOf(request.getParameter("goodId"));
        return  JSONArray.toJSONString(cartService.descGoodNum(userId,goodId));
    }
//    修改数量
@PostMapping ("PUT/UpdateCartGoodNum")
@ResponseBody
    public String updateCartGoodNum(HttpServletRequest request){
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Integer goodId=Integer.valueOf(request.getParameter("goodId"));
        Integer goodNum=Integer.valueOf(request.getParameter("goodNum"));
        return JSONArray.toJSONString(cartService.updateGoodNum(userId,goodId,goodNum));
    }
//    删除购物车中商品
@PostMapping ("DELETE/CartGood")
@ResponseBody
    public String deleteCartGood(HttpServletRequest request){
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Integer goodId=Integer.valueOf(request.getParameter("goodId"));
        return JSONArray.toJSONString(cartService.deleteGoodNum(userId,goodId));
    }
    @PostMapping ("PATCH/CartGood")
    @ResponseBody
    public String updateCartGood(HttpServletRequest request){
        Integer allSelected = Integer.valueOf(request.getParameter("allSelected"));
        Integer selected= Integer.valueOf(request.getParameter("selected"));
        Date date = new Date();
        Integer goodNum = Integer.valueOf(request.getParameter("goodNum"));
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Integer goodId= Integer.valueOf(request.getParameter("goodId"));
        return JSONArray.toJSONString(cartService.updateCart(allSelected,selected,goodNum,date,userId,goodId));
    }
}
