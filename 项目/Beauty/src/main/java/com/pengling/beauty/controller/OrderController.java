package com.pengling.beauty.controller;

import com.alibaba.fastjson.JSONArray;
import com.pengling.beauty.entity.*;
import com.pengling.beauty.mapper.Cart_shopperInfoMapper;
import com.pengling.beauty.mapper.GoodInOrderInfoMapper;
import com.pengling.beauty.mapper.GoodMapper;
import com.pengling.beauty.mapper.OrderInfoMapper;
import com.pengling.beauty.service.GoodService;
import com.pengling.beauty.service.OrderInfoService;
import com.pengling.beauty.service.OrderService;
import com.pengling.beauty.service.ShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("GET/Order/{currentPage}")
    @ResponseBody
    public String showAdminForward(@PathVariable("currentPage") String currentPage ){
        System.out.println("展示订单信息。。");
        Integer pageNum = Integer.parseInt(currentPage);
        Page<Order> orderPage =orderService.selectAll(pageNum);
        System.out.println(orderPage);
        return JSONArray.toJSONString(orderPage);
    }
    @RequestMapping("GET/OrderById/{orderId}")
    @ResponseBody
    public String selectAdminById(@PathVariable("orderId") String orderId){
        System.out.println("展示目标订单信息。。");
        Integer id = Integer.valueOf(orderId);
        List<Order> orderList = orderService.selectByOrderId(id) ;
        System.out.println(orderList);
        return JSONArray.toJSONString(orderList);
    }

    @RequestMapping("GET/OrderBeforeFHById/{orderId}")
    @ResponseBody
    public String selectOrderBeforeFHById(@PathVariable("orderId") String orderId){
        System.out.println("展示目标订单信息。。");
        Integer id = Integer.valueOf(orderId);
        List<Order> orderList = orderService.selectByOrderBeforeFHByOrderId(id) ;
        System.out.println(orderList);
        return JSONArray.toJSONString(orderList);
    }
    @Autowired
    private GoodService goodService;
    @PostMapping("PATCH/OrderStatus")
    @ResponseBody
    public String changeOrderStatus(HttpServletRequest request){
        String orderHao=request.getParameter("orderHao");
        Integer userId= Integer.valueOf(request.getParameter("userId"));
        Integer orderStatus=Integer.valueOf(request.getParameter("orderStatus"));
        return JSONArray.toJSONString(orderService.changeOrderStatus(orderHao,userId,orderStatus));
    }
    @RequestMapping("GET/OrderAfterSH/{currentPage}")
    @ResponseBody
    public String showOrderAfterSH(@PathVariable("currentPage") String currentPage ){
        System.out.println("展示订单信息。。");
        Integer pageNum = Integer.parseInt(currentPage);
        Page<Order> orderPage =orderService.selectByOrderAfterSH(pageNum);
        System.out.println(orderPage);
        return JSONArray.toJSONString(orderPage);
    }

//    查询已收货的订单
@RequestMapping("GET/OrderOrderBeforeFH/{currentPage}")
@ResponseBody
public String showOrderBeforeFH(@PathVariable("currentPage") String currentPage ){
    System.out.println("展示订单信息。。");
    Integer pageNum = Integer.parseInt(currentPage);
    Page<Order> orderPage =orderService.selectByOrderBeforeFH(pageNum);
    System.out.println(orderPage);
    return JSONArray.toJSONString(orderPage);
}
@Autowired
private GoodInOrderInfoMapper goodInOrderInfoMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private Cart_shopperInfoMapper cartShopperInfoMapper;
    @RequestMapping("GET/OrderInfoByOrderHao/{orderHao}/{userId}")
    @ResponseBody
    public String getOrderInfoByOrderHao(@PathVariable("orderHao") String orderHao,@PathVariable("userId") String userId){
        System.out.println("展示订单信息。。");
        Integer userid = Integer.valueOf(userId);
        System.out.println(orderHao);
        OrderInfo orderInfo = orderInfoMapper.getOrderInfo(orderHao).get(0);
        List<Integer> goodList = orderService.selectByOrderHao(orderHao);
        List<GoodInOrderInfo> goodInOrderInfoList = new ArrayList<>();
        for(int i : goodList){
            GoodInOrderInfo goodInOrderInfo = goodInOrderInfoMapper.query(i,userid,orderHao).get(0);
            goodInOrderInfoList.add(goodInOrderInfo);
        }
        orderInfo.setGoodInfo(goodInOrderInfoList);
        return JSONArray.toJSONString(orderInfo);
    }


    @GetMapping("GET/OrderAfterFH/{shopperId}")
    @ResponseBody
    public String getOrderAfterFH(@PathVariable("shopperId") String shopperId){
        System.out.println("发货后的数据");
        Integer shopperid= Integer.valueOf(shopperId);
        List<Order> orderList = orderService.selectByOrderBeforeSH(1,shopperid);
        System.out.println(orderList);
        return JSONArray.toJSONString(orderList);
    }
    @PostMapping("PATCH/OrderOrderBeforeFH/")
    @ResponseBody
    public String insertOrderBeforeFH(HttpServletRequest request ){
        System.out.println("更新快递单号");
        Integer trackingNum = Integer.valueOf(request.getParameter("trackingNum"));
        String trackingShopper = request.getParameter("trackingShopper");
        Integer orderId = Integer.valueOf(request.getParameter("orderId"));
        System.out.println(trackingNum);
        System.out.println(trackingShopper);
        System.out.println(orderId);
        int status=0;
        status=orderService.FH(orderId,trackingNum,trackingShopper);
        return JSONArray.toJSONString(status);
    }
//    生成订单
    @PostMapping("POST/Order")
    @ResponseBody
    public String generateOrder(HttpServletRequest request ){
        System.out.println("新增订单");
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Integer goodId = Integer.valueOf(request.getParameter("goodId"));
        Integer goodNum= Integer.valueOf(request.getParameter("goodNum"));
        Double orderPrice= Double.valueOf(request.getParameter("orderPrice"));
        Integer shopperId = Integer.valueOf(request.getParameter("shopperId"));
        String orderHao= request.getParameter("orderHao");
        Integer orderStatus = Integer.valueOf(request.getParameter("orderStatus"));
        String date = String.valueOf(System.currentTimeMillis());;
        Integer addressId =Integer.valueOf(request.getParameter("addressId"));
        Order order = new Order(userId,goodId,orderHao,orderStatus,goodNum, orderPrice,addressId,date,shopperId);
        return JSONArray.toJSONString(orderService.generateOrder(order));
    }
    @PostMapping("DELETE/Order")
    @ResponseBody
    public String deleteOrder(HttpServletRequest request){
        System.out.println("取消订单");
        String orderHao = request.getParameter("orderHao");
        Integer userId= Integer.valueOf(request.getParameter("userId"));
        return JSONArray.toJSONString(orderService.deleteOrder(orderHao,userId));
    }
}
