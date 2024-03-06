package com.nhnacademy.shoppingmall.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails{
    int quantity;
    int unitCost;
    int orderID;
    int productID;

    public OrderDetails(int quantity, int unitCost){
        this.quantity = quantity;
        this.unitCost = unitCost;
    }
}
