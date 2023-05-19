package com.pengling.beauty.controller;

import com.alibaba.fastjson.JSONArray;
import com.pengling.beauty.entity.Admin;
import com.pengling.beauty.entity.Page;
import com.pengling.beauty.entity.Shopper;
import com.pengling.beauty.entity.User;
import com.pengling.beauty.service.AdminService;
import com.pengling.beauty.service.ShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShopperController {
    @Autowired
    private ShopperService ss;

    @RequestMapping("/ShopperLoginCheck")
    public String Check(HttpServletRequest request, HttpSession session) {
        String account = (String) request.getParameter("account");
        String password = (String) request.getParameter("password");
        System.out.println("电话：" + account);
        System.out.println("密码：" + password);
        try{
            List<Shopper> shopper = ss.login(new Shopper(account,password));
            if (!shopper.isEmpty()) {
                session.setAttribute("shopperId",shopper.get(0).getShopperId());
                session.setAttribute("shopperName",shopper.get(0).getShopperName());
                session.setAttribute("shopperTel",shopper.get(0).getShopperTel());
                return "/ShopperIndex.jsp";
            } else {
                return "/ShopperLogin.jsp";
            }
        }catch (Exception e){
            return "/erro/erro500.jsp";
        }


    }
//正序展示商家信息
    @RequestMapping("GET/shoppers/ASC/{currentPage}")
    @ResponseBody
    public String showShopperForward(@PathVariable("currentPage") String currentPage) {
        System.out.println("展示商家信息。。正序");
        Integer pageNum = Integer.parseInt(currentPage);
        Page<Shopper> shopperPage = ss.queryAllShopperByASC(pageNum);
        System.out.println(shopperPage);
        return JSONArray.toJSONString(shopperPage);
    }
    //倒序展示商家信息
    @RequestMapping("GET/shoppers/DESC/{currentPage}")
    @ResponseBody
    public String showShopperBackward(@PathVariable("currentPage") String currentPage) {
        System.out.println("展示商家信息---倒序。。");
        Integer pageNum = Integer.parseInt(currentPage);
        Page<Shopper> shopperPage = ss.queryAllShopperByDESC(pageNum);
        System.out.println(shopperPage);
        return JSONArray.toJSONString(shopperPage);
    }
/**
 * 删除商家
 */
    @PostMapping("DELETE/shopper")
    @ResponseBody
    public String deleteShopperById(HttpServletRequest request){
        System.out.println("删除商家");
        Integer shopperOpenid = Integer.valueOf(request.getParameter("shopperOpenid"));
        int status = 0 ;
        if (ss.deleteShopperByShopperOpenId(shopperOpenid)==1){
            status=1;
        }
        return JSONArray.toJSONString(status);
    }

    @RequestMapping("GET/User/ShopperId/{username}")
    @ResponseBody
    public String queryShopper(@PathVariable("username") String username ){
        System.out.println("搜索用户。。");
        System.out.println(username);
        List<Shopper> shoppers=null;
        try {
            Integer id = Integer.parseInt(username);
            shoppers = ss.queryShopperByShopperOpenId(id);
        }catch (Exception e){
            shoppers=ss.queryShopperByShopperName(username);
        }
        System.out.println(shoppers);
        Page<Shopper> shopperPage = new Page<Shopper>();
        shopperPage.setResultList(shoppers);
        return JSONArray.toJSONString(shopperPage);
    }

}
