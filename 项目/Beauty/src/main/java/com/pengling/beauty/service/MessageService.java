package com.pengling.beauty.service;

import com.pengling.beauty.entity.Message;
import com.pengling.beauty.entity.Page;
import com.pengling.beauty.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements MessageServiceInt{
@Autowired
private MessageMapper messageMapper;
    @Override
    public Page<Message> selectAllByDateASC(int currentPage) {
        int startNum = (currentPage-1)*10;
        int endNum = 10;
        int count = messageMapper.count();
        int pageCount = count%10==0?count/10:count/10+1;
        Page<Message> messagePage = new Page<>(pageCount,currentPage,messageMapper.selectAllByDateASC(startNum,endNum));
        return messagePage;
    }

    @Override
    public Page<Message> selectAllByDateDESC(int currentPage) {
        int startNum = (currentPage-1)*10;
        int endNum = 10;
        int count = messageMapper.count();
        int pageCount = count%10==0?count/10:count/10+1;
        Page<Message> messagePage = new Page<>(pageCount,currentPage,messageMapper.selectAllByDateDESC(startNum,endNum));
        return messagePage;
    }
}
