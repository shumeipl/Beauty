package com.pengling.beauty.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pengling.beauty.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    /*
管理员登录
 */
    List<Admin> login(Admin admin);

    List<Admin> show(int startNum, int endNum);
//页面大小
    Integer count();
    int add(Admin admin);
    int updateCode(int code ,String adminTel,int adminId);
    int updateDeleteCode(String adminTel,int adminId);
    int deleteByAdminId(int id);
    int editByAdminId(Admin admin);
    List<Admin> queryByAdminId(int id);
    List<Admin> queryByAdminName(String adminName);
}
