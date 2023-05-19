package com.pengling.beauty.mapper;

import com.pengling.beauty.entity.Search;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {
//    拿到userId最近10条的搜索数据
    List<String> getSearchList(Integer userId);
//    新增搜索数据
    int addSearch(Search search);
//    删除搜索数据

}
