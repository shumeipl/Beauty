package com.pengling.beauty.controller;

import com.alibaba.fastjson.JSONArray;
import com.pengling.beauty.entity.Admin;
import com.pengling.beauty.entity.Page;
import com.pengling.beauty.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("LoginCheck")
    public String Check(HttpServletRequest request, HttpSession session){
        String account = (String) request.getParameter("account");
        String password = (String) request.getParameter("password");

        try{
            Admin admin= adminService.login(account,password);
            if (admin!=null) {
                session.setAttribute("admin_id",admin.getAdminId());
                session.setAttribute("admin_name",admin.getAdminName());
                session.setAttribute("admin_phoneNumber",admin.getAdminTel());
                session.setAttribute("admin_idcard",admin.getAdminIdcard());
                session.setAttribute("admin_sex",admin.getAdminSex());
                session.setAttribute("admin_address",admin.getAdminAddress());
                System.out.println("进入主页");
                return "/AdminIndex.jsp";
            }
            else {
                System.out.println("返回登录页");
                return "/AdminLogin.jsp";
            }
        }catch (Exception e){
            return "/erro/erro500.jsp";
        }
    }
    @RequestMapping("GET/Admin/{currentPage}")
    @ResponseBody
    public String showAdminForward(@PathVariable("currentPage") String currentPage ){
        System.out.println("展示用户信息。。");
        Integer pageNum = Integer.parseInt(currentPage);
        List<Admin> admins = adminService.show(pageNum);
        System.out.println(admins);
        Integer pageCount = adminService.count()%5==0?adminService.count()/5:adminService.count()/5+1;
        Page<Admin> adminPage = new Page<Admin>();
        adminPage.setCurrentPage(pageNum);
        adminPage.setPageCount(pageCount);
        adminPage.setResultList(admins);
        System.out.println(adminPage);
        return JSONArray.toJSONString(adminPage);
    }
    @PostMapping("POST/Admin")
    @ResponseBody
    public String addAdmin(HttpServletRequest request){
        System.out.println("添加管理员");
        String name = request.getParameter("name");
        String idCard = request.getParameter("idcard");
        String sex =request.getParameter("sex");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");
        Admin admin = new Admin(name,idCard,sex,tel,address);
        int status = 0;
        status = adminService.add(admin);
        return JSONArray.toJSONString(status);
    }
    @PostMapping("DELETE/Admin/{id}")
    @ResponseBody
    public String deleteAdmin(@PathVariable("id") String id){
        System.out.println("删除管理员");
        Integer admin_id = Integer.valueOf(id);
        int status = 0;
        status = adminService.delete(admin_id);
        return JSONArray.toJSONString(status);
    }
    @PostMapping("PUT/Admin")
    @ResponseBody
    public String updateAdmin(HttpSession session,HttpServletRequest request){
        System.out.println("更新管理员信息");
        String tel = request.getParameter("newTel");
        Integer admin_id = (Integer) session.getAttribute("admin_id");
        Admin admin = new Admin(admin_id,tel);
        int status = 0;
        status = adminService.update(admin);
        if (status==1){
            session.setAttribute("admin_phoneNumber",tel);
        }
        return JSONArray.toJSONString(status);
    }
    @RequestMapping("GET/Admin/AdminId/{username}")
    @ResponseBody
    public String queryAdin(@PathVariable("username") String username ){
        System.out.println("搜索用户。。");
        List<Admin> admins=null;
        try {
            Integer id = Integer.parseInt(username);
            admins = adminService.queryById(id);
        }catch (Exception e){
            admins=adminService.queryByName(username);
        }
        System.out.println(admins);
        Page<Admin> adminPage = new Page<Admin>();
        adminPage.setResultList(admins);
        return JSONArray.toJSONString(adminPage);
    }
    @PostMapping("PUT/SendCode")
    @ResponseBody
    public String sendAdminCode(HttpSession session) throws Exception {
        System.out.println("发送验证码");
        String admin_tel = (String) session.getAttribute("admin_phoneNumber");
        Integer admin_id = (Integer) session.getAttribute("admin_id");
        int status = adminService.updateCode(admin_tel,admin_id);
        return JSONArray.toJSONString(status);
    }
    @PostMapping("PUT/CheckCode")
    @ResponseBody
    public String checkAdminCode(HttpServletRequest request,HttpSession session) throws Exception {
        System.out.println("检查验证码");
        Integer Code = Integer.valueOf(request.getParameter("code"));
        System.out.println(Code);
        Integer admin_id = (Integer) session.getAttribute("admin_id");
        List<Admin> adminList =  adminService.queryById(admin_id);
        Admin admin = adminList.get(0);
        System.out.println(admin.getAdminCode());
        int status = 0;
        System.out.println((int) Code== admin.getAdminCode());
        if (admin.getAdminCode() ==(int) Code){
            System.out.println(1);
            status=1;
        }
        return JSONArray.toJSONString(status);
    }

    @PostMapping("PUT/DeleteCode")
    @ResponseBody
    public String deleteAdminCode(HttpServletRequest request,HttpSession session) throws Exception {
        System.out.println("删除验证码");
        Integer admin_id = (Integer) session.getAttribute("admin_id");
        String admin_tel = (String) session.getAttribute("admin_phoneNumber");
        int status=0;
        status =  adminService.updateDeleteCode(admin_tel,admin_id);
        return JSONArray.toJSONString(status);
    }


}
