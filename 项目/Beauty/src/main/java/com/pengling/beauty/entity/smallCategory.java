package com.pengling.beauty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
@NoArgsConstructor
@Data
public class smallCategory {
    private Integer smallCategoryId;
    private Integer categoryId;
    private String smallCategoryName;
    public smallCategory(String smallCategoryName,Integer smallCategoryId,Integer categoryId){
        this.categoryId=categoryId;
        this.smallCategoryId=smallCategoryId;
        this.smallCategoryName =smallCategoryName;
    }
}
