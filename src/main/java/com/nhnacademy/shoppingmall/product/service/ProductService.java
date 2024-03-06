package com.nhnacademy.shoppingmall.product.service;

import com.nhnacademy.shoppingmall.product.domain.Product;

import java.util.List;

public interface ProductService <T>{
    T get(int id);
    void save(T t);
    void update(T t);
    void delete(int id);
    List<T> toList();
    T findByModelNumber(String modelNumber);
    List<Product> searchProducts(String query);
}
