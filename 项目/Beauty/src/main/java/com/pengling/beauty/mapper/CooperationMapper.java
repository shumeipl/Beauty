package com.pengling.beauty.mapper;

import com.pengling.beauty.entity.Cooperation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CooperationMapper {
    List<Cooperation> selectAll(int startNum,int endNum);
    int insertAll(Cooperation cooperation);
    int accept(Cooperation cooperation);
    int refuse(int cooperationId);
    int count();
    int delete(int cooperationId);
    List<Cooperation> selectById(int id);
    int insert(Cooperation cooperation);
}
