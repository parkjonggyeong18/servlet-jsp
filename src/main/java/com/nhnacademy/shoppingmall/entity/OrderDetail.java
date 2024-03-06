package com.nhnacademy.shoppingmall.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OrderDetails")
public class OrderDetail {

    @EmbeddedId
    private Pk pk;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "UnitCost")
    private double unitCost;

    public static class Pk implements Serializable{
        @Column(name = "OrderID")
        private int orderID;

        @Column(name = "ProductID")
        private int productID;
    }
}
