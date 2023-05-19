package com.pengling.beauty.service;

import com.pengling.beauty.entity.Page;
import com.pengling.beauty.entity.User;

import java.util.Date;
import java.util.List;

public interface UserServiceInt {
    /**
     * 按照日期正序查询所有用户
     * @return用户页
     */
    Page<User> selectAllASC(int currentPage);

    /**
     * 按照日期正序查询所有用户
     * @param currentPage
     * @return用户页
     */
    Page<User> selectAllDESC(int currentPage);

    /**
     * 通过openid查询用户
     * @param userId
     * @return目标用户列表
     */
    List<User> selectByUserId(Integer userId);
    /**
     * 通过userName查询用户
     * @param userName
     * @return目标用户列表
     */
    List<User> selectByUserName(String userName);
    List<User> selectUserByUserOpenId(String userOpenId);
    Integer addUser(User user);
    Integer changeUserNickname(String nickName,Integer userId);
    Integer changeUserGender(String gender,Integer userId);
    Integer changeUserCode(Integer userId,String userTel) throws Exception;
    int updateTel(String tel,Integer userId);
    //    查看积分
    int queryPoint(int userId);
    //    加积分
    int addPoint(int userId);
    //    减积分
    int descPoint(int userId,int thePoint);
    int updateUserTel(String tel, int userId);

    int changeUserAvatar(String gender, Integer userId);
}
