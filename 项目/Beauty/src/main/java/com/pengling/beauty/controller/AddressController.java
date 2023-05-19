package com.pengling.beauty.controller;

import com.alibaba.fastjson.JSONArray;
import com.pengling.beauty.entity.Address;
import com.pengling.beauty.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AddressController {
    @Autowired
    private AddressService addressService;
//    查找
    @GetMapping("GET/AddressList/{userId}")
    @ResponseBody
    public String getAddress(@PathVariable("userId") String userId){
        return JSONArray.toJSONString(addressService.getAddress(Integer.valueOf(userId)));
    }
//    新增
@PostMapping ("PUT/Address")
@ResponseBody
    public String addAddress(HttpServletRequest request){
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        String addressConsignee=request.getParameter("name");
        String addressTel = request.getParameter("tel");
        String detailAddress = request.getParameter("detailAddress");
        Integer addressTag = Integer.valueOf(request.getParameter("tag"));
        Integer addressDefault = Integer.valueOf(request.getParameter("addressDefault"));
        String countryName = request.getParameter("countryName");
        String provinceName=request.getParameter("provinceName");
        String cityName = request.getParameter("cityName");
        String districtName = request.getParameter("districtName");
        int status = addressService.addAddress(new Address(userId,addressConsignee,addressTel,detailAddress,addressTag,addressDefault,countryName,provinceName,cityName,districtName));
        if (status==1){
            return JSONArray.toJSONString("200");
        }
        else{
            return JSONArray.toJSONString("404");
        }
    }
//    修改
@PostMapping("PATCH/Address")
@ResponseBody
    public String modifyAddress(HttpServletRequest request){
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        String addressConsignee=request.getParameter("name");
        String addressTel = request.getParameter("tel");
        String detailAddress = request.getParameter("detailAddress");
        Integer addressTag = Integer.valueOf(request.getParameter("tag"));
        Integer addressDefault = Integer.valueOf(request.getParameter("addressDefault"));
        Integer addressId = Integer.valueOf(request.getParameter("addressId"));
        String countryName = request.getParameter("countryName");
        String provinceName=request.getParameter("provinceName");
        String cityName = request.getParameter("cityName");
        String districtName = request.getParameter("districtName");
        int status = addressService.updateAddress(new Address(userId,addressConsignee,addressTel,detailAddress,addressTag,addressDefault,addressId,countryName,provinceName,cityName,districtName));
        if (status==1){
            return JSONArray.toJSONString("200");
        }
        else{
            return JSONArray.toJSONString("404");
        }
    }
//    删除
@PostMapping("DELETE/Address")
@ResponseBody
    public String deleteAddress(HttpServletRequest request){
        return JSONArray.toJSONString(addressService.deleteAddress(Integer.valueOf(request.getParameter("addressId"))));
    }
}
