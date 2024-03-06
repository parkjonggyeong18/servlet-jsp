package com.nhnacademy.shoppingmall.order.repository;

import com.nhnacademy.shoppingmall.order.domain.Orders;

import java.util.List;
import java.util.Optional;

public interface OrderRepository <T>{
    Optional<T> findById(int id);
    int save(T t);
    int deleteById(int id);
    int update(T t);
    int countById(int id);
    Optional<Integer> totalCount();

    int saveAndGetOrderId(Orders orders);

    int saveAndGetPrimaryKey(Orders orders);

    List<Orders> getOrdersByUserId(String userId);
}

