package com.nhnacademy.shoppingmall.product.repository;

import com.nhnacademy.shoppingmall.product.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository <T>{
    Optional<T> findById(int id);
    int save(T t);
    int deleteById(int id);
    int update(T t);
    int countById(int id);
    Optional<Integer> totalCount();
    Optional<T> findByModelNumber(String modelNumber);

    List<Product> findByKeyword(String keyword);
}
