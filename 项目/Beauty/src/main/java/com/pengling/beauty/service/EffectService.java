package com.pengling.beauty.service;

import com.pengling.beauty.entity.effect;
import com.pengling.beauty.mapper.effectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EffectService implements EffectServiceInt{
    @Autowired
    private effectMapper effectMapper1;
    @Override
    public List<effect> getAllEffect() {
        return effectMapper1.getAllEffect();
    }
}
