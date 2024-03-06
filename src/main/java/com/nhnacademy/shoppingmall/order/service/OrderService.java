package com.nhnacademy.shoppingmall.order.service;

import com.nhnacademy.shoppingmall.order.domain.OrderDetails;
import com.nhnacademy.shoppingmall.order.domain.Orders;
import com.nhnacademy.shoppingmall.shoppingcart.domain.ShoppingRecord;

import java.util.List;

public interface OrderService <T>{
    T get(int id);
    void save(T t);
    void update(T t);
    void delete(int id);
    List<T> toList();
    int saveAndGetOrderId(Orders orders);
    List<Orders> getOrdersByUserId(String userId);
}
