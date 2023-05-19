package com.pengling.beauty.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.swing.event.ListDataEvent;
import java.util.List;
@Repository
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Page<T>  {
//    总页数
    private Integer pageCount;
//    当前页数
    private Integer currentPage;
//    当前页数据
    private List<T> resultList;

    public Page(List<T> resultList) {
        this.resultList = resultList;
    }
}