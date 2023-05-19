package com.pengling.beauty.service;

import com.pengling.beauty.entity.Admin;
import com.pengling.beauty.mapper.AdminMapper;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService implements AdminServiceInt{
    @Autowired
    private AdminMapper am;
    @Override
    public Admin login(String account, String password) {
        Admin admin = new Admin(account,password);
        System.out.println("am");
        System.out.println(am.login(admin).get(0));
        if (am.login(admin)!=null){
            return am.login(admin).get(0);
        }
        else{
            return null;
        }
    }

    @Override
    public List<Admin> show(int pageNum) {
        return am.show((pageNum-1)*5,pageNum*5);
    }

    @Override
    public Integer count() {
        return am.count();
    }

    @Override
    public int add(Admin admin) {
        System.out.println("添加新用户");
        return am.add(admin);
    }

    @Override
    public int delete(int id) {
        System.out.println("删除用户");
        return am.deleteByAdminId(id);
    }

    @Override
    public int update(Admin admin) {
        return  am.editByAdminId(admin);
    }

    @Override
    public List<Admin> queryById(int id) {
        return am.queryByAdminId(id);
    }

    @Override
    public List<Admin> queryByName(String adminName) {
        return am.queryByAdminName(adminName);
    }
    ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "112980", "f5510980-2340-4df9-87d6-f2a287ba92df");
    @Override
    public int updateCode( String adminTel,int adminId) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("number", adminTel);
        params.put("templateId", "11375");
        String[] templateParams = new String[2];
        int random = (int) ((Math.random()*9+1)*1000);
        System.out.println(random);
        templateParams[0] = random+"";
        templateParams[1] = "5分钟";
        params.put("templateParams", templateParams);
        String result = client.send(params);
        System.out.println(result);
        int status =  am.updateCode(random,adminTel,adminId);
        return status;
    }

    @Override
    public int updateDeleteCode(String adminTel,int adminId) {
        int status = 0 ;
        status=am.updateDeleteCode(adminTel,adminId);
        return status;
    }

}
