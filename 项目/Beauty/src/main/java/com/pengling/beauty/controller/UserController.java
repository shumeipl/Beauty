package com.pengling.beauty.controller;

import com.alibaba.fastjson.JSONArray;
import com.pengling.beauty.entity.Admin;
import com.pengling.beauty.entity.Page;
import com.pengling.beauty.entity.User;
import com.pengling.beauty.service.UserService;
import com.pengling.beauty.util.BeautyThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Component
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("GET/UserASC/{currentPage}")
    @ResponseBody
    public String showAdminForward(@PathVariable("currentPage") String currentPage ){
        System.out.println("展示用户-买家信息。。");
        Integer pageNum = Integer.parseInt(currentPage);
        Page<User> userPage = userService.selectAllASC(pageNum);
        System.out.println(userPage);
        return JSONArray.toJSONString(userPage);
    }
    @RequestMapping("GET/UserDESC/{currentPage}")
    @ResponseBody
    public String showAdminBackward(@PathVariable("currentPage") String currentPage ){
        System.out.println("展示用户-买家信息。。");
        Integer pageNum = Integer.parseInt(currentPage);
        Page<User> userPage = userService.selectAllDESC(pageNum);
        System.out.println(userPage);
        return JSONArray.toJSONString(userPage);
    }
    @RequestMapping("GET/User/UserId/{username}")
    @ResponseBody
    public String queryAdin(@PathVariable("username") String username ){
        System.out.println("搜索用户。。");
        System.out.println(username);
        List<User> users=null;
        try {
            Integer id = Integer.parseInt(username);
            users = userService.selectByUserId(id);
        }catch (Exception e){
            users=userService.selectByUserName(username);
        }
        System.out.println(users);
        Page<User> userPage = new Page<User>();
        userPage.setResultList(users);
        return JSONArray.toJSONString(userPage);
    }
    @RequestMapping("GET/UserByOpenId/{openid}")
    @ResponseBody
    public String getUserInfoByOpenid(@PathVariable("openid") String openid ){
        System.out.println("展示用户-买家信息。。");
        List<User> userList=userService.selectUserByUserOpenId(openid);
        if (userList.isEmpty()){
            return JSONArray.toJSONString(null);
        }
        return JSONArray.toJSONString(userList.get(0));
    }
    @PostMapping("PUT/User")
    @ResponseBody
    public String addUser(HttpServletRequest request){
        System.out.println("新增用户");
        System.out.println(request);
        String nickName =request.getParameter("nickName");
        System.out.println(nickName);
        String gender=request.getParameter("gender");
        String openid=request.getParameter("openid");
        String avatarUrl=request.getParameter("avatarUrl");
        System.out.println(openid);
        User user = new User(nickName,gender,openid,avatarUrl);
        int status=userService.addUser(user);
        return JSONArray.toJSONString(status);
    }
    @PostMapping("PUT/ChangeUserNickname")
    @ResponseBody
    public String changeUserNickName(HttpServletRequest request){
        String nickName =request.getParameter("nickName");
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        System.out.println(nickName);
        int status=userService.changeUserNickname(nickName,userId);
        return JSONArray.toJSONString(status);
    }
    @PostMapping("PUT/ChangeUserGender")
    @ResponseBody
    public String changeUserGender(HttpServletRequest request){
        String gender =request.getParameter("gender");
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        int status=userService.changeUserGender(gender,userId);
        return JSONArray.toJSONString(status);
    }
    @PostMapping("PUT/ChangeUserAvatar")
    @ResponseBody
    public String changeUserAvatar(HttpServletRequest request){
        String gender =request.getParameter("avatar");
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        int status=userService.changeUserAvatar(gender,userId);
        return JSONArray.toJSONString(status);
    }
    @PostMapping("PUT/SendCodeToUser")
    @ResponseBody
    public String sendCode(HttpServletRequest request) throws Exception {
        String tel =request.getParameter("tel");
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        int status=userService.changeUserCode(userId,tel);
//        staus 1表示ok,开启一个新线程，5分钟内将消息删除
        if (status==1) {
            BeautyThread beautyThread = new BeautyThread();
            beautyThread.setUserCode("");
            beautyThread.setUserId(userId);
            beautyThread.start();
        }
        return JSONArray.toJSONString(status);
    }
//    得到用户信息
    @PostMapping("PUT/IdentifyCode")
    @ResponseBody
    public String changeIdentify(HttpServletRequest request) throws Exception {
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        List<User> userList=userService.selectByUserId(userId);
        System.out.println(userList);
        System.out.println(userList.get(0).getUserCode());
        return JSONArray.toJSONString(userList.get(0).getUserCode());
    }
    @PostMapping("PUT/UpdateUserTel")
    @ResponseBody
    public String changeUserTel(HttpServletRequest request) throws Exception {
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        String tel = request.getParameter("tel");
        return JSONArray.toJSONString(userService.updateUserTel(tel,userId));
    }
}
