package com.pengling.beauty.mapper;

import com.pengling.beauty.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    List<Message> selectAllByDateASC(int startNum,int endNum);
    List<Message> selectAllByDateDESC(int startNum,int endNum);
    int count();
}
