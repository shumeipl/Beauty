package com.pengling.beauty.userController;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.pengling.beauty.entity.User;
import com.pengling.beauty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@Controller
public class loginController {
    @Autowired
    private UserService userService;
    @PostMapping("POST/user-login")
    public String login(HttpServletRequest request){
        String userOpenId=request.getParameter("userOpenId");
        String userNickname=request.getParameter("userNickname");
        String userAvatar=request.getParameter("userAvatar");
        String userSex=request.getParameter("userSex");
        List<User> userList = userService.selectUserByUserOpenId(userOpenId);
        if (userList.isEmpty()){
            int status  = add(new User(userNickname,userSex,userOpenId,userAvatar));
            if (status==1){
                login(request);
            }
        }
        else {
            return JSONArray.toJSONString(userList);
        }
        return null;
    }
    public Integer add(User user){
        int status = userService.addUser(user);
        return status;
    }
}
