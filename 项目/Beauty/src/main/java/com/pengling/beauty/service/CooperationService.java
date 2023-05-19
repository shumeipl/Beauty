package com.pengling.beauty.service;

import com.pengling.beauty.entity.Cooperation;
import com.pengling.beauty.entity.Page;
import com.pengling.beauty.mapper.CooperationMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CooperationService implements  CooperationServiceInt{
    @Autowired
    private CooperationMapper cooperationMapper;
    @Override
    public Page<Cooperation> selectAll(int currentPage) {
        int startNum = (currentPage-1)*10;
        int endNum =10;
        int pageCount =cooperationMapper.count()%10==0?cooperationMapper.count()/10:cooperationMapper.count()/10+1;
        List<Cooperation> cooperationList = cooperationMapper.selectAll(startNum,endNum);
        Page<Cooperation> cooperationPage = new Page<>(pageCount,currentPage,cooperationList);
        return cooperationPage;
    }

    @Override
    public int accept(int id) {
        Cooperation cooperation = cooperationMapper.selectById(id).get(0);
        int status = cooperationMapper.accept(cooperation);
        int res=0;
        if (status==1){
            res = cooperationMapper.delete(id);
        }
        return res;
    }

    @Override
    public int refuse(int id) {
        return cooperationMapper.refuse(id);
    }

    @Override
    public int insertAll(Cooperation cooperation) {
        return cooperationMapper.insertAll(cooperation);
    }

    @Override
    public int insert(Cooperation cooperation) {
        return cooperationMapper.insert(cooperation);
    }
}
