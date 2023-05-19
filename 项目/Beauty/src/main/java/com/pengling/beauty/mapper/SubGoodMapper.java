package com.pengling.beauty.mapper;

import com.pengling.beauty.entity.SubGood;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubGoodMapper {
    int insertSubGoodAdd(SubGood subGood) ;
}
