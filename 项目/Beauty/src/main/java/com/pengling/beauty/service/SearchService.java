package com.pengling.beauty.service;

import com.pengling.beauty.entity.Search;
import com.pengling.beauty.mapper.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService implements SearchServiceInt {
    @Autowired
    private SearchMapper searchMapper;
    @Override
    public List<String> getSearchList(Integer userId) {
        return searchMapper.getSearchList(userId);
    }

    @Override
    public int addSearch(Search search) {
        return searchMapper.addSearch(search);
    }
}
