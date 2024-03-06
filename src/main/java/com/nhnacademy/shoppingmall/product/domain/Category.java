package com.nhnacademy.shoppingmall.product.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Category {
    private int categoryID;
    private String categoryName;

    public Category(String categoryName){
        this.categoryName = categoryName;
    }
}