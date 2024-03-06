package com.nhnacademy.shoppingmall.product.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Product {
    private int productID;
    private int categoryID;
    private String modelNumber;
    private String modelName;
    private int quantity;
    private String productImage;
    private int unitCost;
    private String comment;

    public Product(int categoryID, String modelNumber, String modelName, int quantity, String productImage, int unitCost, String comment){
        this.categoryID = categoryID;
        this.modelNumber = modelNumber;
        this.modelName = modelName;
        this.quantity = quantity;
        this.productImage = productImage;
        this.unitCost = unitCost;
        this.comment = comment;
    }
}
