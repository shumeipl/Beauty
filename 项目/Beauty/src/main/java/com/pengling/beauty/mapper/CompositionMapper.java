package com.pengling.beauty.mapper;

import com.pengling.beauty.entity.Composition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompositionMapper {
    List<Composition> queryCompositionInfo(String compositionName);
}
