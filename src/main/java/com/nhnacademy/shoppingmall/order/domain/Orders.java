package com.nhnacademy.shoppingmall.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    private int orderID;
    private String userID;
    private LocalDateTime orderDate;
    private LocalDateTime shipDate;

    public Orders(String userID, LocalDateTime orderDate, LocalDateTime shipDate){
        this.userID = userID;
        this.orderDate = orderDate;
        this.shipDate = shipDate;

    }

}
