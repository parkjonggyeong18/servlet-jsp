package com.nhnacademy.shoppingmall.order.service;

import com.nhnacademy.shoppingmall.order.domain.OrderDetails;
import com.nhnacademy.shoppingmall.shoppingcart.domain.ShoppingRecord;

import java.util.List;

public interface OrderDetailsService<T> {
    T get(int id);
    void save(T t);
    void update(T t);
    void delete(int id);
    List<T> toList();
    List<OrderDetails> getOrderDetailsByOrderId(int orderId);
}