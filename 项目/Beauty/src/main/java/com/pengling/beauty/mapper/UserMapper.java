package com.pengling.beauty.mapper;

import com.pengling.beauty.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 通过Userid查询用户
     * @param userId
     * @return
     */
    List<User> selectUserByUserId(Integer userId);

    /**
     * 通过用户名查询用户
     * @param userName
     * @return
     */
    List<User> selectUserByUserName(String userName);

    /**
     * 查询所有用户日期正序
     * @return
     */
    List<User> selectAllUserASC(Integer startNum,Integer endNum);
    /**
     * 查询所有用户日期倒序
     * @return
     */
    List<User> selectAllUserDESC(Integer startNum,Integer endNum);

    /**
     * 查询用户列表有多少页
     * @return
     */
    int count();

    /**
     * 根据openid查询用户信息。用户id和用户个人信息
     * @return
     */
    List<User> selectUserByOpenId(String userOpenid);

    /**
     * 添加新用户
     * @param user
     * @return
     */
    int addUser(User user);
    int ChangeUserNickName(String nickName,Integer userId);
    int changeUserGender(String gender,Integer userId);
//    发送 or 删掉验证码
    int changeUserCode(String userCode,Integer userId);
//    校验 得到userCode
//    更新电话号码
    int updateTel(String tel,Integer userId);
//    查看积分
    int queryPoint(int userId);
//    加积分
    int addPoint(int userId, Date date);
//    减积分
    int descPoint(int userId,int thePoint);

    int changeUserAvatar(String avatar, Integer userId);
}
