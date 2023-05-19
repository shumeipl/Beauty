package com.pengling.beauty.mapper;

import com.pengling.beauty.entity.smallCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SmallCategoryMapper {
    List<smallCategory> selectSmallCategoryByCategoryName(String name);
}
