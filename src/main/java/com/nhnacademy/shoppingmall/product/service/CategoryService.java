package com.nhnacademy.shoppingmall.product.service;

import java.util.List;

public interface CategoryService <T>{
    T get(int id);
    void save(T t);
    void update(T t);
    void delete(int id);
    List<T> toList();


}
