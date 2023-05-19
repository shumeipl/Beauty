package com.pengling.beauty.service;

import com.pengling.beauty.entity.Complain;
import com.pengling.beauty.entity.Page;

import java.util.List;


public interface ComplainServiceInt {
    Page<Complain> selectAll(Integer currentPage);
    Page<Complain> selectByGoodName(Integer currentPage,String goodName);
    int insert(Complain complain);

    Page<Complain> selectByGoodId(Integer currentPage, Integer goodId);
}
