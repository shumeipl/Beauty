package com.pengling.beauty.service;

import com.pengling.beauty.entity.smallCategory;
import com.pengling.beauty.mapper.SmallCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SmallCategoryService implements SmallCategoryServiceInt{
    @Autowired
    private SmallCategoryMapper smallCategoryMapper;
    @Override
    public List<smallCategory> getSmallCategoryByCategoryName(String name) {
        return smallCategoryMapper.selectSmallCategoryByCategoryName(name);
    }
}
