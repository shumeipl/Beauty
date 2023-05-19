package com.pengling.beauty.service;

import com.pengling.beauty.entity.Message;
import com.pengling.beauty.entity.Page;

public interface MessageServiceInt {
    Page<Message> selectAllByDateASC(int currentPage);
    Page<Message> selectAllByDateDESC(int currentPage);
}
