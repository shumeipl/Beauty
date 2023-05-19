package com.pengling.beauty.service;

import com.pengling.beauty.entity.CartInfo;

import java.util.List;

public interface CartInfoServiceInt {
    List<CartInfo> getCartInfo(Integer userId);
}
