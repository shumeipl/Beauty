package com.pengling.beauty.service;

import com.pengling.beauty.entity.Admin;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminServiceInt {
/*
管理员登录
 */
Admin login(String account, String password);
List<Admin> show(int pageNum);
Integer count();
int add(Admin admin);
int delete(int id);
int update(Admin admin);
List<Admin> queryById(int id);
List<Admin> queryByName(String adminName);

    int updateCode(String adminTel,int adminId) throws Exception;
    int updateDeleteCode(String adminTel,int adminId);
}
