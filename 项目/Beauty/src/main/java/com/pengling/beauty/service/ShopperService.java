package com.pengling.beauty.service;

import com.pengling.beauty.entity.Page;
import com.pengling.beauty.entity.Shopper;
import com.pengling.beauty.mapper.ShopperMapper;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopperService implements ShopperServiceInt {
    @Autowired
    private ShopperMapper sm;
    @Override
    public List<Shopper> login(Shopper shopper) {
        if (!sm.login(shopper).isEmpty()){
            return sm.login(shopper);
        }else {
            return null;
        }
    }

    @Override
    public Page<Shopper> queryAllShopperByASC(int pageNum) {
        int startNum = (pageNum-1)*10;
        int endNum = 10;
        int pageCount =sm.ShopperCount()%10==0?sm.ShopperCount()/10:sm.ShopperCount()/10+1;
        Page<Shopper> shopperPage = new Page<>(pageCount,pageNum,sm.queryAllShoppersByASC(startNum,endNum));
        return shopperPage;

    }

    @Override
    public Page<Shopper> queryAllShopperByDESC(int pageNum) {
        int startNum = (pageNum-1)*10;
        int endNum = 10;
        int pageCount =sm.ShopperCount()%10==0?sm.ShopperCount()/10:sm.ShopperCount()/10+1;
        Page<Shopper> shopperPage = new Page<>(pageCount,pageNum,sm.queryAllShoppersByDESC(startNum,endNum));
        return shopperPage;
    }

    @Override
    public List<Shopper> queryShopperByShopperOpenId(Integer openid) {
        return sm.queryAllByShopperOpenidShoppers(openid);
    }

    @Override
    public List<Shopper> queryShopperByShopperName(String name) {
        return sm.queryAllByShopperNameShoppers(name);
    }

    @Override
    public int deleteShopperByShopperOpenId(Integer openid) {
        return sm.deleteByShopperOpenid(openid);
    }

    @Override
    public int shopperCount() {
        return sm.ShopperCount();
    }

    @Override
    public int insertByShopper(Shopper shopper) {
        return sm.insertShopperById(shopper);
    }

    @Override
    public int updateByShopperId(Shopper shopper) {
        return sm.updateShopperById(shopper);
    }

    @Override
    public int updateByPassword(int shopperId, String password) {
        Shopper shopper = new Shopper(shopperId,password);
        return sm.updateShopperPasswordById(shopper);
    }
    ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "112980", "f5510980-2340-4df9-87d6-f2a287ba92df");

    @Override
    public int updateCode( int shopperTel,int shopperId) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("number", shopperTel+"");
        params.put("templateId", "11375");
        String[] templateParams = new String[2];
        int random = (int) ((Math.random()*9+1)*1000);
        System.out.println(random);
        templateParams[0] = random+"";
        templateParams[1] = "5分钟";
        params.put("templateParams", templateParams);
        String result = client.send(params);
        System.out.println(result);
        int status = sm.updateCode(random,shopperTel,shopperId);
        return status;
    }

    @Override
    public int updateDeleteCode(int shopperTel,int shopperId) {
        int status = sm.updateDeleteCode(shopperTel,shopperId);
        return status;
    }
}
