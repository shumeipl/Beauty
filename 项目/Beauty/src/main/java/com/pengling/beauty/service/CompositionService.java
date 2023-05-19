package com.pengling.beauty.service;

import com.pengling.beauty.entity.Composition;
import com.pengling.beauty.mapper.CompositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompositionService implements CompositionServiceInt{
    @Autowired
    private CompositionMapper compositionMapper;
    @Override
    public List<Composition> queryCompositionInfo(String name) {
        return compositionMapper.queryCompositionInfo(name);
    }
}
