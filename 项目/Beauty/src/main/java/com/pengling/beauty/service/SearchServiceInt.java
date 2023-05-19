package com.pengling.beauty.service;

import com.pengling.beauty.entity.Search;

import java.util.List;

public interface SearchServiceInt {
    //    拿到userId最近10条的搜索数据
    List<String> getSearchList(Integer userId);
    //    新增搜索数据
    int addSearch(Search search);
}
