package com.pengling.beauty.mapper;

import com.pengling.beauty.entity.effect;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface effectMapper {
    List<effect> getAllEffect();
}
