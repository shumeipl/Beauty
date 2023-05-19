package com.pengling.beauty.mapper;

import com.pengling.beauty.entity.GoodInOrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodInOrderInfoMapper {
    List<GoodInOrderInfo> query(int goodId,int userId,String s);

    List<GoodInOrderInfo> queryAll(int goodId, int userId);

}
