package com.pengling.beauty.service;

import com.pengling.beauty.entity.smallCategory;

import java.util.List;

public interface SmallCategoryServiceInt {
    List<smallCategory> getSmallCategoryByCategoryName(String name);
}
