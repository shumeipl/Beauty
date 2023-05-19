package com.pengling.beauty.service;

import com.pengling.beauty.entity.SubGood;
import com.pengling.beauty.mapper.SubGoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubGoodService implements SubGoodServiceInt {
@Autowired
private SubGoodMapper subGoodMapper;
    @Override
    public Integer insert(SubGood subGood) {
        return subGoodMapper.insertSubGoodAdd(subGood);
    }
}
