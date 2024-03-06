package com.nhnacademy.shoppingmall.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private int productID;

    @Column(name = "CategoryID")
    private int categoryID;

    @Column(name = "ModelNumber")
    private String modelNumber;

    @Column(name = "ModelName")
    private String modelName;

    @Column(name = "ProductImage")
    private String productImage;

    @Column(name = "UnitCost")
    private double unitCost;

    @Column(name = "Description")
    private String description;

}