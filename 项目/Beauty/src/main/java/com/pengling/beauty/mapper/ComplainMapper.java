package com.pengling.beauty.mapper;

import com.pengling.beauty.entity.Complain;
import com.pengling.beauty.entity.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ComplainMapper {
//    查看所有的投诉
    List<Complain> selectAllComplainDESC(Integer startNum, Integer endNum);
//    查看某个商品的投诉 parm:goodid
    List<Complain> selectByGoodId(Integer goodId,Integer startNum,Integer endNum);
    //    查看某个商品的投诉 parm:goodname
    List<Complain> selectByGoodName(String goodName,Integer startNum,Integer endNum);
    int count();
    int selectByGoodIdCount(Integer goodId);
    int selectByGoodNameCount(String goodName);
    int insert(Complain complain);
}
