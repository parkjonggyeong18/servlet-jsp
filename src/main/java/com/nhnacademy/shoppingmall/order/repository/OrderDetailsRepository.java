package com.nhnacademy.shoppingmall.order.repository;

import com.nhnacademy.shoppingmall.order.domain.OrderDetails;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsRepository<T>{
    Optional<T> findById(int id);
    int save(T t);
    int deleteById(int id);
    int update(T t);
    int countById(int id);
    Optional<Integer> totalCount();

    List<OrderDetails> getOrderDetailsByOrderId(int orderId);
}

