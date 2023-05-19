package com.pengling.beauty.service;

import com.pengling.beauty.entity.Page;
import com.pengling.beauty.entity.User;
import com.pengling.beauty.mapper.UserMapper;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
@Service
public class UserService implements UserServiceInt{
    @Autowired
    private UserMapper userMapper;
    /**
     * 按照日期正序查询所有用户
     *
     * @param currentPage
     * @return用户页
     */
    @Override
    public Page<User> selectAllASC(int currentPage) {
        int startNum = (currentPage-1)*10;
        int endNum = 10;
        int count = userMapper.count();
        int pageCount = count%10==0?count/10:count/10+1;
        List<User> userList = userMapper.selectAllUserASC(startNum,endNum);
        Page<User> userPage = new Page<>(pageCount,currentPage,userList);
        return userPage;
    }

    /**
     * 按照日期正序查询所有用户
     *
     * @param currentPage
     * @return用户页
     */
    @Override
    public Page<User> selectAllDESC(int currentPage) {
        int startNum = (currentPage-1)*10;
        int endNum = 10;
        int count = userMapper.count();
        int pageCount = count%10==0?count/10:count/10+1;
        List<User> userList = userMapper.selectAllUserDESC(startNum,endNum);
        Page<User> userPage = new Page<>(pageCount,currentPage,userList);
        return userPage;
    }

    /**
     * 通过openid查询用户
     *
     * @param openid
     * @return目标用户列表
     */
    @Override
    public List<User> selectByUserId(Integer openid) {
        return userMapper.selectUserByUserId(openid);
    }

    /**
     * 通过userName查询用户
     *
     * @param userName
     * @return目标用户列表
     */
    @Override
    public List<User> selectByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }

    @Override
    public List<User> selectUserByUserOpenId(String userOpenId) {
        return userMapper.selectUserByOpenId(userOpenId);
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public Integer changeUserNickname(String nickName, Integer userId) {
        return userMapper.ChangeUserNickName(nickName,userId);
    }

    @Override
    public Integer changeUserGender(String gender, Integer userId) {
        return userMapper.changeUserGender(gender,userId);
    }
    ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "112980", "f5510980-2340-4df9-87d6-f2a287ba92df");
//   发送验证码
    @Override
    public Integer changeUserCode(Integer userId,String userTel) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("number", userTel);
        params.put("templateId", "11375");
        String[] templateParams = new String[2];
        int random = (int) ((Math.random()*9+1)*1000);
        System.out.println(random);
        templateParams[0] = random+"";
        templateParams[1] = "5分钟";
        params.put("templateParams", templateParams);
        String result = client.send(params);
        System.out.println(result);
        return userMapper.changeUserCode(random+"",userId);
    }

    @Override
    public int updateTel(String tel, Integer userId) {
        return userMapper.updateTel(tel,userId);
    }

    @Override
    public int queryPoint(int userId) {
        return userMapper.queryPoint(userId);
    }

    @Override
    public int addPoint(int userId) {
        Date date = new Date();
        return userMapper.addPoint(userId,date);
    }

    @Override
    public int descPoint(int userId, int thePoint) {
        return userMapper.descPoint(userId,thePoint);
    }

    @Override
    public int updateUserTel(String tel, int userId) {
        return userMapper.updateTel(tel,userId);
    }

    @Override
    public int changeUserAvatar(String avatar, Integer userId) {
        return userMapper.changeUserAvatar(avatar,userId);
    }

}
