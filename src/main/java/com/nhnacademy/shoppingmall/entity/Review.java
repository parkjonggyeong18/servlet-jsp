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
@Table(name = "Reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReviewID")
    private int reviewID;

    @Column(name = "ProductID")
    private int productID;

    @Column(name = "CustomerID")
    private int customerID;

    @Column(name = "Rating")
    private int rating;

    @Column(name = "Comments")
    private String comments;

}
