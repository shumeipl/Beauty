package com.pengling.beauty.service;

import com.pengling.beauty.entity.Cooperation;
import com.pengling.beauty.entity.Page;

public interface CooperationServiceInt {
    Page<Cooperation> selectAll(int currentPage);
    int accept(int id);
    int refuse(int id);
    int insertAll(Cooperation cooperation);
    int insert(Cooperation cooperation);
}
