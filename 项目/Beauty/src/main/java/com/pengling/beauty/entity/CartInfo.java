package com.pengling.beauty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class CartInfo {
    private Integer shopper_id;
    private String shopper_name;
    private Integer allSelected;
    private List<Cart_goodInfo> goodInfos;
}
