package com.pengling.beauty.service;

import com.pengling.beauty.entity.Complain;
import com.pengling.beauty.entity.Page;
import com.pengling.beauty.mapper.ComplainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplainService implements ComplainServiceInt{
    @Autowired
    private ComplainMapper complainMapper;
    @Override
    public Page<Complain> selectAll(Integer currentPage) {
        int startNum = (currentPage-1)*10;
        int endNum=10;
        List<Complain> complainList = complainMapper.selectAllComplainDESC(startNum,endNum);
        Page<Complain> complainPage = new Page<>(complainMapper.count(),currentPage,complainList);
        return complainPage;
    }

    @Override
    public Page<Complain> selectByGoodName(Integer currentPage,String goodName) {
        int startNum = (currentPage-1)*10;
        int endNum=10;
        List<Complain> complainList = complainMapper.selectByGoodName(goodName,startNum,endNum);
        Page<Complain> complainPage = new Page<>(complainMapper.selectByGoodNameCount(goodName),currentPage,complainList);
        return complainPage;
    }

    @Override
    public int insert(Complain complain) {
        return complainMapper.insert(complain);
    }

    @Override
    public Page<Complain> selectByGoodId(Integer currentPage,Integer goodId) {
        int startNum = (currentPage-1)*10;
        int endNum=10;
        List<Complain> complainList = complainMapper.selectByGoodId(goodId,startNum,endNum);
        int pageCount =complainMapper.selectByGoodIdCount(goodId)%10==0?complainMapper.selectByGoodIdCount(goodId)/10:
                complainMapper.selectByGoodIdCount(goodId)/10+1;
        Page<Complain> complainPage = new Page<>(pageCount,currentPage,complainList);
        return complainPage;
    }
}
