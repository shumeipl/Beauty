package com.pengling.beauty.service;
import com.pengling.beauty.entity.Composition;

import java.util.List;


public interface CompositionServiceInt {
    List<Composition> queryCompositionInfo(String name);
}
