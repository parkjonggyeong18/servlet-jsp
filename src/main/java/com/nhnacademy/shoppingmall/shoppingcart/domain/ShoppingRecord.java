package com.nhnacademy.shoppingmall.shoppingcart.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShoppingRecord {
    private int recordID;
    private String cartID;
    private int quantity;
    private int productID;
    private LocalDateTime dateCreated;

    public ShoppingRecord(String cartID, int quantity, int productID, LocalDateTime dateCreated){
        this.cartID = cartID;
        this.quantity = quantity;
        this.productID = productID;
        this.dateCreated = dateCreated;
    }
}
