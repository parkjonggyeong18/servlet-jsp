package com.nhnacademy.shoppingmall.shoppingcart.repositroy;

import java.util.Optional;

public interface ShoppingCartRepository <T>{
    Optional<T> findById(int id);
    int save(T t);
    int deleteById(int id);
    int update(T t);
    int countById(int id);
    Optional<Integer> totalCount();
}
