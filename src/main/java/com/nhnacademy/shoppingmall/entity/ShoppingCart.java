package com.nhnacademy.shoppingmall.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ShoppingCart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RecordID")
    private int recordID;

    @Column(name = "CartID")
    private String cartID;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "ProductID")
    private int productID;

    @Column(name = "DateCreated")
    private Date dateCreated;

}
