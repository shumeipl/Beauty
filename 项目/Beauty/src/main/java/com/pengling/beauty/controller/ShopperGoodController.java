package com.pengling.beauty.controller;

import com.alibaba.fastjson.JSONArray;
import com.pengling.beauty.entity.Good;
import com.pengling.beauty.entity.Page;
import com.pengling.beauty.entity.SubGood;
import com.pengling.beauty.service.GoodService;
import com.pengling.beauty.service.SubGoodService;
import com.pengling.beauty.util.uploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@Controller
public class ShopperGoodController {
    @Autowired
    private GoodService goodService;
    @Autowired
    private SubGoodService subGoodService;
    @RequestMapping("GET/goods/{currentPage}")
    @ResponseBody
//   拿到已上架的商品
    public String getAllGoods( HttpSession session, @PathVariable("currentPage") String currentPage){
        int shopperId= (int) session.getAttribute("shopperId");
        Page<Good> goodPage = goodService.queryRoost(Integer.parseInt(currentPage),shopperId);
        System.out.println(goodPage);
        return JSONArray.toJSONString(goodPage);
    }
    @RequestMapping("GET/goodsChecking/{currentPage}")
    @ResponseBody
//   拿到审核中的商品
    public String getAllGoodsChecking( HttpSession session, @PathVariable("currentPage") String currentPage){
        int shopperId= (int) session.getAttribute("shopperId");
        Page<Good> goodPage = goodService.queryChecking(Integer.parseInt(currentPage),shopperId);
        System.out.println(goodPage);
        return JSONArray.toJSONString(goodPage);
    }
    @RequestMapping("GET/goodsRefuse/{currentPage}")
    @ResponseBody
//   拿到被拒绝上架的商品
    public String getAllGoodsRefuse( HttpSession session, @PathVariable("currentPage") String currentPage){
        int shopperId= (int) session.getAttribute("shopperId");
        Page<Good> goodPage = goodService.queryRefuse(Integer.parseInt(currentPage),shopperId);
        System.out.println(goodPage);
        return JSONArray.toJSONString(goodPage);
    }
    @RequestMapping("GET/goodsByCategory/{smallCategory}")
    @ResponseBody
    public String getAllGoodsByCategory( HttpSession session,@PathVariable("smallCategory") String smallCategory){
        List<Good> goodList =goodService.selectByCategory(Integer.parseInt(smallCategory));
        return JSONArray.toJSONString(goodList);
    }
    @PostMapping("PUT/good")
    public String putAGood(HttpSession session, HttpServletRequest request, MultipartFile goodImg1,
                           MultipartFile goodImg2,
                           MultipartFile goodImg3,MultipartFile goodDetail,
        MultipartFile goodMaterial
    ) throws IOException {
        System.out.println("增加一条新记录");
        int shopperId = (int) session.getAttribute("shopperId");
        String goodName = request.getParameter("goodName");
        String goodIntroduct=request.getParameter("goodIntroduct");
        Double goodPrice = Double.valueOf(request.getParameter("goodPrice"));
        String Img1 = uploadUtil.uploadImage(goodImg1);
        String Img2 =uploadUtil.uploadImage(goodImg2);
        String Img3 =uploadUtil.uploadImage(goodImg3);
        Integer goodStorage = Integer.valueOf(request.getParameter("goodStorage"));
        String goodCategory = request.getParameter("goodCategory");
        String goodFeature = request.getParameter("goodFeature");
        String goodSmallCategory = request.getParameter("goodSmallCategory");
        String goodCertificationNumber = request.getParameter("goodCertificateNumber");
        String Material = uploadUtil.uploadImage(goodMaterial);
        Integer goodSoldNum = 0;
        String goodVideo = request.getParameter("goodVideo");
        Integer goodStatus =0;
        int status = goodService.insert(new Good(shopperId,goodName,goodPrice,Img1, Img2, Img3, goodStorage,goodVideo,goodCategory,goodSmallCategory,goodCertificationNumber,Material,goodSoldNum,goodIntroduct,goodStatus,goodFeature));
        return "/Shopper/SUCCESS.jsp";
    }
    @PostMapping("DELETE/Good")
    @ResponseBody
    public String deleteGood(HttpServletRequest request){
        Integer goodId = Integer.valueOf(request.getParameter("goodId"));
        return JSONArray.toJSONString(goodService.deleteGoodByGoodId(goodId));
    }
}
