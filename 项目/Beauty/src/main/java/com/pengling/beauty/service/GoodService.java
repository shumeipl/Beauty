package com.pengling.beauty.service;

import com.pengling.beauty.entity.Good;
import com.pengling.beauty.entity.Page;
import com.pengling.beauty.mapper.GoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GoodService implements GoodServiceInt{
    @Autowired
    private GoodMapper goodMapper;
    @Override
    public Page<Good> selectAllReadyGood(int currentPage) {
        int startNum = (currentPage-1)*10;
        int endNum=currentPage*10;
        int count = goodMapper.countReadyGood();
        if (count%10==0){
            count=count/10;
        }
        else{
            count=count/10+1;
        }
        List<Good> goodList = goodMapper.selectAllReadyGood(startNum,endNum);
        Page<Good> goodPage = new Page<>(count,currentPage,goodList);
        return goodPage;
    }

    @Override
    public int acceptReadyGood(int goodId) {
        return goodMapper.acceptReadyGood(goodId);
    }

    @Override
    public int refuseReadyGood(int goodId) {
        return goodMapper.refuseReadyGood(goodId);
    }

    @Override
    public Page<Good> selectAllAlreadyGood(int currentPage) {
        int startNum = (currentPage-1)*10;
        int endNum=currentPage*10;
        int count = goodMapper.countAlreadyGood();
        List<Good> goodList = goodMapper.selectAllAlreadyGood(startNum,endNum);
        Page<Good> goodPage = new Page<>(count,currentPage,goodList);
        return goodPage;
    }

    @Override
    public int refuseAlreadyGood(int goodId) {
        return refuseReadyGood(goodId);
    }

    @Override
    public List<Good> selectByIdAlreadyGood(int goodId) {
        return goodMapper.selectByIdAlreadyGood(goodId);
    }

    @Override
    public Page<Good> selectAll(int currentPage,int shopperId) {
        int startNum=(currentPage-1)*10;
        int endNum=10;
        List<Good> goodList =goodMapper.selectAll(startNum,endNum,shopperId);
        int count = goodMapper.countAll();
        int pageCount = count%10==0?count/10:count/10+1;
        Page<Good> goodPage= new Page<>(pageCount,currentPage,goodList);
        System.out.println(goodPage);
        return goodPage;
    }

    @Override
    public int insert(Good good) {
        return goodMapper.insert(good);
    }

    @Override
    public int selectId(Integer shopperId, String goodName) {
        return goodMapper.selectId(shopperId,goodName);
    }

    @Override
    public int shangJiaGood(int goodId) {
        return goodMapper.shangJiaGood(goodId);
    }

    @Override
    public int xiaJiaGood(int goodId) {
        return goodMapper.xiaJiaGood(goodId);
    }

    @Override
    public List<Good> selectByIdGood(int goodId) {
        return goodMapper.selectByIdGood(goodId);
    }

    @Override
    public List<Good> selectByCategory(int CategoryId) {
        return goodMapper.selectByCategory(CategoryId);
    }

    @Override
    public Page<Good> selectByGoodSelected(int pageNum, int pageSize) {
        List<Good> goodList =goodMapper.selectByGoodSelected(pageNum-1,pageSize);
        int count = goodMapper.countSelectByGoodSelected();
        int pageCount = count%pageSize==0?count/pageSize:count/pageSize+1;
        Page<Good> goodPage= new Page<>(pageCount,pageNum,goodList);
        return goodPage;
    }

    @Override
    public int deleteGoodByGoodId(Integer goodId) {
        return goodMapper.deleteGoodByGoodId(goodId);
    }

    @Override
    public List<Good> findGoodByFeature(String feature) {
        return goodMapper.findGoodByFeature(feature);
    }

    @Override
    public List<Good> findGoodByName(String goodName) {
        return goodMapper.findGoodByName(goodName);
    }

    @Override
    public int updateDetail(String path,Integer goodId) {
        return goodMapper.updateDetail(path,goodId);
    }

    @Override
    public List<String> getMaterial(Integer goodId) {
        return goodMapper.getMaterial(goodId);
    }

    @Override
    public List<Good> getHotSale() {
        Long date = System.currentTimeMillis();
        date=date-7*24*60*60*1000;
        System.out.println(date);
        return goodMapper.getHotSale(String.valueOf(date));
    }

    @Override
    public Page<Good> queryRoost(int currentPage, int shopperId) {
        int startNum=(currentPage-1)*10;
        int endNum=10;
        List<Good> goodList =goodMapper.queryRoost(shopperId,startNum,endNum);
        System.out.println(goodList);
        int count =goodMapper.countAlreadyGood();
        int pageCount = count%10==0?count/10:count/10+1;
        Page<Good> goodPage= new Page<>(pageCount,currentPage,goodList);
        System.out.println(goodPage);
        return goodPage;
    }

    @Override
    public Page<Good> queryChecking(int currentPage, int shopperId) {
        int startNum=(currentPage-1)*10;
        int endNum=10;
        List<Good> goodList =goodMapper.queryChecking(shopperId,startNum,endNum);
        int count = goodMapper.countReadyGood();
        int pageCount = count%10==0?count/10:count/10+1;
        Page<Good> goodPage= new Page<>(pageCount,currentPage,goodList);
        System.out.println(goodPage);
        return goodPage;
    }

    @Override
    public Page<Good> queryRefuse(int currentPage, int shopperId) {
        int startNum=(currentPage-1)*10;
        int endNum=10;
        List<Good> goodList =goodMapper.queryRefuse(shopperId,startNum,endNum);
        int countZ = goodMapper.countAll();
        int count1=goodMapper.countAlreadyGood();
        int count0=goodMapper.countReadyGood();
        int  count=countZ-count0-count1;
        int pageCount = count%10==0?count/10:count/10+1;
        Page<Good> goodPage= new Page<>(pageCount,currentPage,goodList);
        System.out.println(goodPage);
        return goodPage;
    }

    @Override
    public int becomeSelected(int goodId) {
        return goodMapper.becomeSelected(goodId);
    }

    @Override
    public List<Good> selectAllSupplement(int currentPage, int shopperId) {
        return goodMapper.selectAllSupplement( shopperId, (currentPage-1)*10,10);
    }
}
